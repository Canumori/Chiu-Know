#!/usr/bin/env python3
"""Generate one review-only CHIU KNOW? character voice pilot with open-source Chatterbox.

This script never writes generated audio into Android resources. It produces a temporary
pilot for human review. Only already-approved bundled character samples are accepted as
voice references.
"""

from __future__ import annotations

import argparse
import hashlib
import json
import os
from pathlib import Path


ROOT = Path(__file__).resolve().parents[2]

CHARACTERS = {
    "mia": {
        "reference": ROOT / "app/src/main/res/raw/mia_voice_sample_girl.mp3",
        "exaggeration": 0.45,
        "cfg_weight": 0.45,
    },
    "chiu": {
        "reference": ROOT / "app/src/main/res/raw/chiu_voice_sample_expressive.wav",
        "exaggeration": 0.78,
        "cfg_weight": 0.30,
    },
}

SUPPORTED_LANGUAGES = {"en", "pt", "es", "fr", "ko"}


def sha256(path: Path) -> str:
    digest = hashlib.sha256()
    with path.open("rb") as handle:
        for chunk in iter(lambda: handle.read(1024 * 1024), b""):
            digest.update(chunk)
    return digest.hexdigest()


def load_request(path: Path) -> dict:
    with path.open("r", encoding="utf-8") as handle:
        request = json.load(handle)
    required = {"character", "language", "text"}
    missing = sorted(required - set(request))
    if missing:
        raise ValueError(f"Missing request fields: {', '.join(missing)}")
    return request


def validate_request(request: dict) -> tuple[str, str, str, float, float]:
    character = str(request["character"]).strip().lower()
    language = str(request["language"]).strip().lower()
    text = str(request["text"]).strip()

    if character not in CHARACTERS:
        raise ValueError(f"Unsupported character: {character}")
    if language not in SUPPORTED_LANGUAGES:
        raise ValueError(f"Unsupported language: {language}")
    if not text:
        raise ValueError("Text must not be empty")
    if len(text) > 300:
        raise ValueError("Pilot text must be 300 characters or fewer")

    defaults = CHARACTERS[character]
    exaggeration = float(request.get("exaggeration", defaults["exaggeration"]))
    cfg_weight = float(request.get("cfg_weight", defaults["cfg_weight"]))
    if not 0.0 <= exaggeration <= 1.0:
        raise ValueError("exaggeration must be between 0 and 1")
    if not 0.0 <= cfg_weight <= 1.0:
        raise ValueError("cfg_weight must be between 0 and 1")

    return character, language, text, exaggeration, cfg_weight


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--request", type=Path, required=True)
    parser.add_argument("--output-dir", type=Path, required=True)
    parser.add_argument("--validate-only", action="store_true")
    args = parser.parse_args()

    request = load_request(args.request)
    character, language, text, exaggeration, cfg_weight = validate_request(request)
    reference = CHARACTERS[character]["reference"]
    if not reference.is_file():
        raise FileNotFoundError(f"Approved reference not found: {reference}")

    model_family = "chatterbox-english" if language == "en" else "chatterbox-multilingual-v3"
    print(f"Character: {character}")
    print(f"Language: {language}")
    print(f"Model: {model_family}")
    print(f"Reference: {reference.relative_to(ROOT)}")
    print(f"Reference SHA-256: {sha256(reference)}")
    print(f"Text: {text}")

    if args.validate_only:
        print("Validation only: generation skipped.")
        return

    import torch
    import torchaudio as ta

    # GitHub's standard public Linux runner has four CPUs. Keep inference bounded to the
    # available runner instead of oversubscribing threads.
    torch.set_num_threads(max(1, min(4, os.cpu_count() or 1)))
    device = "cpu"  # Pass a string; this also avoids the known torch.device CPU loader bug.

    if language == "en":
        from chatterbox.tts import ChatterboxTTS

        model = ChatterboxTTS.from_pretrained(device=device)
        wav = model.generate(
            text,
            audio_prompt_path=str(reference),
            exaggeration=exaggeration,
            cfg_weight=cfg_weight,
        )
    else:
        from chatterbox.mtl_tts import ChatterboxMultilingualTTS

        model = ChatterboxMultilingualTTS.from_pretrained(device=device, t3_model="v3")
        wav = model.generate(
            text,
            language_id=language,
            audio_prompt_path=str(reference),
            exaggeration=exaggeration,
            cfg_weight=cfg_weight,
        )

    args.output_dir.mkdir(parents=True, exist_ok=True)
    audio_path = args.output_dir / f"{character}_{language}_pilot.wav"
    ta.save(str(audio_path), wav.cpu(), model.sr)

    metadata = {
        "status": "PILOT_ONLY_NOT_APPROVED",
        "character": character,
        "language": language,
        "text": text,
        "model": model_family,
        "device": device,
        "reference_path": str(reference.relative_to(ROOT)),
        "reference_sha256": sha256(reference),
        "exaggeration": exaggeration,
        "cfg_weight": cfg_weight,
        "audio_filename": audio_path.name,
        "audio_sha256": sha256(audio_path),
    }
    metadata_path = args.output_dir / "metadata.json"
    metadata_path.write_text(json.dumps(metadata, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

    print(f"Generated pilot: {audio_path}")
    print(f"Audio SHA-256: {metadata['audio_sha256']}")
    print("Status: PILOT_ONLY_NOT_APPROVED")


if __name__ == "__main__":
    main()
