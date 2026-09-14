#!/usr/bin/env python3
"""Automated linguistic QA for temporary CHIU KNOW? voice pilots.

This is a hard pre-human gate. The user is never asked to judge pronunciation in languages
she does not speak. Both the native-language source and the character-converted output must
remain recognisable as the requested text.

ASR intelligibility is not a perfect certificate of native accent. It is one independent
barrier against wrong/missing words; language-specific source models are the first barrier.
Additional phonetic alignment can be added per language before production audio is approved.
"""

from __future__ import annotations

import argparse
import json
import re
import unicodedata
from pathlib import Path

from jiwer import cer, wer


LANGUAGE_MAP = {
    "en": "en",
    "pt": "pt",
    "es": "es",
    "fr": "fr",
    "ko": "ko",
}


def normalize(text: str) -> str:
    text = unicodedata.normalize("NFKC", text).casefold()
    chars = []
    for ch in text:
        category = unicodedata.category(ch)
        if ch.isspace():
            chars.append(" ")
        elif category.startswith("L") or category.startswith("N"):
            chars.append(ch)
        else:
            chars.append(" ")
    return re.sub(r"\s+", " ", "".join(chars)).strip()


def transcribe(model, audio_path: Path, language: str) -> tuple[str, dict]:
    segments, info = model.transcribe(
        str(audio_path),
        language=LANGUAGE_MAP[language],
        beam_size=5,
        vad_filter=True,
        condition_on_previous_text=False,
    )
    transcript = " ".join(segment.text.strip() for segment in segments).strip()
    return transcript, {
        "language": info.language,
        "language_probability": getattr(info, "language_probability", None),
        "duration": getattr(info, "duration", None),
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--request", type=Path, required=True)
    parser.add_argument("--output-dir", type=Path, required=True)
    parser.add_argument("--model", default="small")
    parser.add_argument("--max-word-error-rate", type=float, default=0.0)
    parser.add_argument("--max-korean-character-error-rate", type=float, default=0.0)
    args = parser.parse_args()

    request = json.loads(args.request.read_text(encoding="utf-8"))
    character = str(request["character"]).strip().lower()
    language = str(request["language"]).strip().lower()
    expected = str(request["text"]).strip()

    if language not in LANGUAGE_MAP:
        raise ValueError(f"Unsupported QA language: {language}")

    source_path = args.output_dir / f"source_{language}.wav"
    final_path = args.output_dir / f"{character}_{language}_pilot.wav"
    for path in (source_path, final_path):
        if not path.is_file():
            raise FileNotFoundError(path)

    from faster_whisper import WhisperModel

    model = WhisperModel(args.model, device="cpu", compute_type="int8")
    expected_norm = normalize(expected)
    results = {}
    failures = []

    for label, path in (("source", source_path), ("converted", final_path)):
        transcript, info = transcribe(model, path, language)
        transcript_norm = normalize(transcript)
        word_error = wer(expected_norm, transcript_norm)
        character_error = cer(expected_norm, transcript_norm)

        results[label] = {
            "audio": path.name,
            "transcript": transcript,
            "normalized_transcript": transcript_norm,
            "word_error_rate": word_error,
            "character_error_rate": character_error,
            **info,
        }

        threshold = (
            args.max_korean_character_error_rate
            if language == "ko"
            else args.max_word_error_rate
        )
        observed = character_error if language == "ko" else word_error
        if observed > threshold:
            failures.append(
                f"{label}: error rate {observed:.4f} exceeded threshold {threshold:.4f}; "
                f"expected={expected_norm!r}, got={transcript_norm!r}"
            )

    report = {
        "status": "PASS" if not failures else "FAIL",
        "character": character,
        "language": language,
        "expected": expected,
        "normalized_expected": expected_norm,
        "asr_model": f"faster-whisper/{args.model}",
        "word_error_threshold": args.max_word_error_rate,
        "korean_character_error_threshold": args.max_korean_character_error_rate,
        "results": results,
        "failures": failures,
        "note": (
            "ASR exactness is an intelligibility/content gate, not a complete native-accent "
            "certificate. Production approval also requires a language-specific source model "
            "and the project voice-quality policy."
        ),
    }
    report_path = args.output_dir / "linguistic_qa.json"
    report_path.write_text(json.dumps(report, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

    metadata_path = args.output_dir / "metadata.json"
    if metadata_path.is_file():
        metadata = json.loads(metadata_path.read_text(encoding="utf-8"))
        metadata["linguistic_qa"] = report["status"]
        metadata["linguistic_qa_report"] = report_path.name
        metadata_path.write_text(json.dumps(metadata, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

    print(json.dumps(report, ensure_ascii=False, indent=2))
    if failures:
        raise SystemExit("Linguistic QA failed: " + " | ".join(failures))


if __name__ == "__main__":
    main()
