# CHIU KNOW? — GitHub storage cleanup — 2026-09-14

This file records the current storage-maintenance state so later chats do not repeat destructive cleanup or remove required build state.

## Historical cleanup already completed

- The repository previously accumulated 471 active `chiu-know-debug` APK artifacts.
- Commit `761b18d37454cfbdd4ae867e6b4d4ba8fd12cd81` stopped normal pushes from storing APKs, set deliberate APK retention to 3 days, and deleted 461 older artifacts while keeping the 10 newest.
- Commit `f80767bf443468a6bbae0d0eb8b3094af13d3c5b` reduced the active debug-APK cap from 10 to 3.
- The current Android CI still compiles and tests every push, but stores an APK only for `workflow_dispatch` or a commit explicitly marked `[apk]`.
- The current cleanup step keeps at most 3 active `chiu-know-debug` artifacts.

## Current audit

- Repository size is about 1.9 MB; source/history files are not the storage problem and must not be deleted merely to save GitHub quota.
- Android CI #560 reported exactly 3 active `chiu-know-debug` artifacts. A recent artifact is about 14 MB, so the active APK-artifact footprint is small and bounded.
- `gradle/actions/setup-gradle@v4` already performs Gradle cache cleanup on successful builds.
- The debug signing cache `chiu-know-debug-keystore-v2` is only a few KB and is REQUIRED for installing future APKs over the currently installed build. Never delete or rotate it casually.
- The Gradle dependency/cache entries are useful build acceleration and are governed separately from APK artifact storage. Do not purge them blindly.

## New preventive optimization

Commit `88478d007a146afc470014351806b4d55f25ab03` adds read-only Gradle caching for documentation-only pushes. Such commits may restore existing Gradle caches for fast verification but must not write new Gradle cache entries.

This reduces unnecessary cache churn from frequent documentation checkpoints without weakening CI, changing application code, changing signing, or touching CHIU PLAYER.

## Permanent maintenance policy

- Keep the 3-APK / 3-day deliberate-artifact policy unless there is a concrete reason to change it.
- Preserve the stable signing cache.
- Preserve historical project documentation; it is small and required for continuity.
- Prefer preventing unnecessary cache writes over aggressive deletion of useful Gradle caches.
- If storage pressure is suspected later, inspect real Actions logs/cache usage before deleting anything.
- Never touch any CHIU PLAYER repository, artifact, cache, workflow, site, Cloudflare resource, or Supabase resource while maintaining CHIU KNOW?.
