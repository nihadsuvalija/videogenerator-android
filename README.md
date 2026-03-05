# VideoGenerator — Android App

A full Android port of the Kotlin desktop video generator script, rebuilt as an MVVM + Clean Architecture Android application.

---

## Architecture

```
app/
├── data/
│   ├── local/
│   │   ├── dao/          # Room DAOs (VideoBatchDao, ImageBatchDao, GeneratedVideoDao)
│   │   ├── entity/       # Room entities
│   │   └── VideoGeneratorDatabase.kt
│   └── repository/
│       ├── Mappers.kt                    # Entity ↔ Domain mappers
│       ├── RepositoryImpl.kt             # Batch & history repo impls
│       └── VideoGeneratorRepositoryImpl.kt  # Core FFmpeg generation logic
├── domain/
│   ├── model/            # Pure Kotlin domain models (no Android deps)
│   ├── repository/       # Repository interfaces
│   └── usecase/          # One use case per operation
├── presentation/
│   ├── adapter/          # RecyclerView ListAdapters
│   ├── ui/
│   │   ├── batch/        # BatchesFragment + Video/Image tab fragments
│   │   ├── generate/     # GenerateFragment
│   │   ├── history/      # HistoryFragment
│   │   └── main/         # MainActivity
│   └── viewmodel/        # HiltViewModels
├── di/                   # Hilt modules (DatabaseModule, RepositoryModule)
└── util/                 # AppPaths, Extensions
```

### Key design decisions
- **MVVM** — ViewModels expose `StateFlow` / `SharedFlow`; Fragments collect from them.
- **Clean Architecture** — UI → ViewModel → UseCase → Repository → Data source. The domain layer has zero Android imports.
- **Room** — Three tables: `video_batches`, `image_batches`, `generated_videos`.
- **Hilt** — DI throughout; `@HiltViewModel`, `@HiltAndroidApp`, `@InstallIn(SingletonComponent)`.
- **Coroutines** — All I/O dispatched on `Dispatchers.IO`; progress updates via lambdas emitted into `StateFlow`.

---

## Features

### Batches Screen
- Two tabs: **Videos** and **Images**
- FAB opens folder picker (SAF `ACTION_OPEN_DOCUMENT_TREE`)
- Batch folder name is used as the `BATCH_XXX` identifier
- Video/image count shown per batch
- Swipe to delete

### Generate Screen
- **Toggle** between *Video Concat* and *Image Loop* modes
- Spinners for: Source Batch, Resolution, Text Category, Font
- Font size, logo toggle, repeat count inputs
- Image Loop: image duration + total duration fields
- Live **progress card** with percentage + message during generation

### History Screen
- Lists all generated videos with type, resolution, duration, timestamp
- **Play** (opens in system video player), **Share**, and **Delete** actions
- Shows "FILE MISSING" badge if output was deleted from disk

---

## Generation Logic (faithfully ported from original Kotlin)

### Video Concat (`VideoGeneratorRepositoryImpl.generateConcatenatedVideo`)
1. Scans `BATCH_XXX` folder for `.mp4` files
2. Cuts a random 1-second slice from each video with colour grading (`eq=saturation=0.25`)
3. Builds an ffmpeg `concat` filter graph
4. Optionally draws multi-line text with `drawtext` (centred, outlined)
5. Optionally overlays the Waymark Noire logo at 85% height
6. Scales/pads to target resolution
7. Encodes with `libx264 -crf 18 -preset ultrafast`

### Image Loop (`VideoGeneratorRepositoryImpl.generateImageLoopVideo`)
1. Scans `BATCH_XXX` folder for `.jpg` / `.jpeg` files
2. For each `ceil(totalDuration / imageDuration)` clips: picks a random image, applies scale+crop+text+logo
3. Writes a `concat_list.txt` and does a final concat pass

---

## Setup

### Prerequisites
- Android Studio Hedgehog (2023.1) or newer
- Android SDK 26+
- **`ffmpeg` and `ffprobe` binaries must be available on the device's PATH**

> ⚠️ The app shells out to the system `ffmpeg` binary (matching the original desktop script). On a real device this requires a rooted device or a bundled binary. For a production build, replace `VideoGeneratorRepositoryImpl.executeFFmpeg()` and `probeVideo()` with [FFmpegKit](https://github.com/arthenica/ffmpeg-kit) — the command arguments are identical.

### FFmpegKit drop-in replacement
Add to `build.gradle.kts`:
```kotlin
implementation("com.arthenica:ffmpeg-kit-full:6.0-2")
```
Then in `VideoGeneratorRepositoryImpl`:
```kotlin
// Replace executeFFmpeg(args) with:
FFmpegKit.executeAsync(args.joinToString(" ")) { session -> ... }

// Replace probeVideo(file) with:
FFprobeKit.getMediaInformation(file.absolutePath)
```

### Build
```bash
./gradlew assembleDebug
```

---

## Asset files bundled
| Asset | Path in APK |
|---|---|
| lock_in.json | `assets/text/lock_in.json` |
| motivational.json | `assets/text/motivational.json` |
| Helvetica.ttf | `assets/font/Helvetica.ttf` |
| Times New Roman.ttf | `assets/font/times-new-roman.ttf` |
| Waymark Noire logo | `assets/logo/waymark_noire_transparent.png` |

Fonts and logo are extracted to the app's cache dir at runtime so ffmpeg can reference them by absolute path.

---

## Output location
`/sdcard/Android/data/com.videogenerator/files/generated/output/video/video_<timestamp>.mp4`
