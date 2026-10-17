# User List KMP — Claude Sonnet Implementation

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.7.1-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/)
[![AI model](https://img.shields.io/badge/Generated_with-Claude_Sonnet-D97757?logo=anthropic&logoColor=white)](https://www.anthropic.com/claude)

A Kotlin Multiplatform user-list application generated with Claude Sonnet. It loads users from the [DummyJSON `/users` endpoint](https://dummyjson.com/users), caches successful responses, and shares a Compose Multiplatform UI across Android, iOS, Desktop, and Web.

## About the experiment

This repository is one of three implementations produced from the same task for the article [**AI Models for a KMP Developer**](https://levelup.gitconnected.com/ai-models-for-a-kmp-developer-fe4c8de26336).

Each model received the same KMP application brief and a second pass for issue fixing. The generated projects are intentionally preserved as separate results instead of being normalized into one architecture. This makes their architectural choices and different levels of completeness directly comparable.

| Implementation | Model | Repository |
| --- | --- | --- |
| Alternative | GPT | [UserListKmpGPT](https://github.com/TarasovVP/UserListKmpGPT) |
| **This project** | **Claude Sonnet** | **UserListKmpSonnet** |
| Alternative | Gemini | [UsersAppGemini (private)](https://github.com/TarasovVP/UsersAppGemini) |

## Features

- Shared user-list UI for Android, iOS, Desktop, and Web.
- Ktor client for loading remote users.
- SQLDelight cache on Android, iOS, and Desktop.
- Offline fallback: a failed network request returns cached users when available.
- In-memory local storage for the Web target.
- Koin dependency injection.
- Coil image loading.
- Shared repository tests.

## Architecture and modules

```text
.
├── shared/       # Domain models, repository contract, networking, and presentation logic
├── data/         # SQLDelight-backed persistence for Android, iOS, and Desktop
├── composeApp/   # Shared Compose UI and platform entry points
└── iosApp/       # Native SwiftUI host for the shared Compose framework
```

`UserRepositoryImpl` uses a network-first strategy: it saves a successful response through the local data source, then falls back to locally stored users when the request fails. If neither source can provide data, the original error is propagated to the UI layer.

## Technology stack

- Kotlin Multiplatform
- Compose Multiplatform and Material 3
- Ktor
- SQLDelight
- Koin
- Coil
- Kotlin Coroutines and Serialization

## Run and verify

```bash
# Android
./gradlew :composeApp:assembleDebug

# Desktop
./gradlew :composeApp:run

# Web (Wasm)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Shared tests
./gradlew :shared:allTests

# Compile the iOS Simulator framework
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

The native iOS application can also be opened from `iosApp/iosApp.xcodeproj` in Xcode.

## Implementation notes

- The Web target deliberately uses an in-memory local data source, so its cache does not survive a page reload.
- Android, iOS, and Desktop share the SQLDelight-backed `data` module.
- This repository preserves the generated and issue-fixing passes used in the comparison; it is an experiment and reference implementation rather than a production application.
