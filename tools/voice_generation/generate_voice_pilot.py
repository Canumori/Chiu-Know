#!/usr/bin/env python3
"""Generate one review-only CHIU KNOW? character voice pilot.

Pronunciation and character identity are deliberately separated:
1. create a source utterance with a native-language TTS model and NO character reference;
2. convert that utterance to an approved character timbre with Chatterbox VC.

Generated audio is never written into Android resources automatically. Every result remains
PILOT_ONLY_NOT_APPROVED until automated linguistic QA passes and the user approves only
character identity/naturalness.
"""

from __future__ import annotations

import argparse
import gc
import hashlib
import json
import os
from pathlib import Path


ROOT = Path(__file__).resolve().parents[2]
CHARACTERS = {
    "mia": ROOT / "app/src/main/res/raw/mia_voice_sample_girl.mp3",
    "chiu": ROOT / "app/src/main/res/raw/chiu_voice_sample_expressive.wav",
}
SUPPORTED_LANGUAGES = {"en", "pt", "es", "fr", "ko"}
# Generation is enabled language-by-language only after its pronunciation source and QA path
# have been proved. English is the first controlled pilot because the rejected sample failed
# specifically on "where".
GENERATION_READY_LANGUAGES = {"en"}


def sha256(path: Path) -> str:
    digest = hashlib.sha256()
    with path.open("rb") as handle:
        for chunk in iter(lambda: handle.read(1024 * 1024), b""):
            digest.update(chunk)
    return digest.hexdigest()


def load_request(path: Path) -> dict:
    request = json.loads(path.read_text(encoding="utf-8"))
    missing = sorted({"character", "language", "text"} - set(request))
    if missing:
        raise ValueError(f"Missing request fields: {', '.join(missing)}")
    return request


def validate_request(request: dict) -> tuple[str, str, str]:
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
    return character, language, text


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--request", type=Path, required=True)
    parser.add_argument("--output-dir", type=Path, required=True)
    parser.add_argument("--validate-only", action="store_true")
    args = parser.parse_args()

    request = load_request(args.request)
    character, language, text = validate_request(request)
    reference = CHARACTERS[character]
    if not reference.is_file():
        raise FileNotFoundError(f"Approved reference not found: {reference}")

    print(f"Character: {character}")
    print(f"Language: {language}")
    print(f"Reference: {reference.relative_to(ROOT)}")
    print(f"Reference SHA-256: {sha256(reference)}")
    print(f"Text: {text}")
    print("Pipeline: native pronunciation source -> character voice conversion")

    if args.validate_only:
        print("Validation only: generation skipped.")
        return

    if language not in GENERATION_READY_LANGUAGES:
        raise RuntimeError(
            f"Generation for '{language}' is intentionally blocked until its language-specific "
            "pronunciation source and automated QA path are proved."
        )

    import torch
    import torchaudio as ta
    from chatterbox.tts_turbo import ChatterboxTurboTTS
    from chatterbox.vc import ChatterboxVC

    torch.set_num_threads(max(1, min(4, os.cpu_count() or 1)))
    device = "cpu"
    args.output_dir.mkdir(parents=True, exist_ok=True)

    source_path = args.output_dir / f"source_{language}.wav"
    final_path = args.output_dir / f"{character}_{language}_pilot.wav"

    # Stage 1: pronunciation. English uses the dedicated English Nano model and its bundled
    # voice, not Mia/Chiu. This prevents a Portuguese reference clip from contaminating the
    # English pronunciation.
    source_model_name = "chatterbox-nano-english-builtin-voice"
    source_model = ChatterboxTurboTTS.from_pretrained(device=device, nano=True)
    source_wav = source_model.generate(text)
    ta.save(str(source_path), source_wav.cpu(), source_model.sr)
    del source_wav, source_model
    gc.collect()

    # Stage 2: identity. Voice conversion changes the timbre to the approved character
    # reference while keeping the linguistic content from the source utterance.
    vc_model_name = "chatterbox-vc"
    vc_model = ChatterboxVC.from_pretrained(device)
    final_wav = vc_model.generate(
        audio=str(source_path),
        target_voice_path=str(reference),
    )
    ta.save(str(final_path), final_wav.cpu(), vc_model.sr)
    del final_wav, vc_model
    gc.collect()

    metadata = {
        "status": "PILOT_ONLY_NOT_APPROVED",
        "character": character,
        "language": language,
        "text": text,
        "device": device,
        "pipeline": "native_source_then_voice_conversion",
        "source_model": source_model_name,
        "voice_conversion_model": vc_model_name,
        "reference_path": str(reference.relative_to(ROOT)),
        "reference_sha256": sha256(reference),
        "source_audio_filename": source_path.name,
        "source_audio_sha256": sha256(source_path),
        "audio_filename": final_path.name,
        "audio_sha256": sha256(final_path),
        "linguistic_qa": "PENDING",
    }
    (args.output_dir / "metadata.json").write_text(
        json.dumps(metadata, ensure_ascii=False, indent=2) + "\n",
        encoding="utf-8",
    )

    print(f"Generated source: {source_path}")
    print(f"Generated pilot: {final_path}")
    print("Status: PILOT_ONLY_NOT_APPROVED; linguistic QA still required")


if __name__ == "__main__":
    main()
