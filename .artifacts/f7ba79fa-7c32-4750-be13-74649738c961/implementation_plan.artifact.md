# Fix GitHub Action failure: Unresolved reference 'CatalogoCafesScreen'

The GitHub Action is failing because the `CatalogoCafesScreen` and its dependencies are defined in `androidMain`, which makes them inaccessible to the `wasmJs` target. This plan outlines moving the shared UI code to `commonMain` and migrating resources to Compose Multiplatform resources.

## User Review Required

> [!IMPORTANT]
> This change moves your UI logic from `androidMain` to `commonMain`. This is the standard way to share code between Android and Web in Kotlin Multiplatform.
>
> [!NOTE]
> I will be migrating your images to `composeResources`. This will allow them to be used on both Android and Web.

## Proposed Changes

### [Shared UI, Theme, and Data Model]

Move the core UI, theme, and data models to `commonMain` so they can be compiled for both Android and WASM.

#### [NEW] [App.kt](file:///F:/Projectes/Aplicacion_Cafes/CapsulasGaribaldi/app/src/commonMain/kotlin/com/example/capsulasgaribaldi/App.kt)
- Contains `CapsulaCafe` data class.
- Contains `CatalogoCafesScreen` composable.
- Uses `Res.drawable` instead of `R.drawable`.

#### [NEW] [Theme](file:///F:/Projectes/Aplicacion_Cafes/CapsulasGaribaldi/app/src/commonMain/kotlin/com/example/capsulasgaribaldi/ui/theme/)
- Move `Color.kt`, `Theme.kt`, and `Type.kt` to `commonMain`.
- Update `Theme.kt` to be platform-independent (handling `dynamicColor` only on Android).

#### [MODIFY] [MainActivity.kt](file:///F:/Projectes/Aplicacion_Cafes/CapsulasGaribaldi/app/src/androidMain/kotlin/com/example/capsulasgaribaldi/MainActivity.kt)
- Remove redundant declarations moved to `commonMain`.
- Use the shared `CapsulasGaribaldiTheme` and `CatalogoCafesScreen`.

#### [MODIFY] [main.kt](file:///F:/Projectes/Aplicacion_Cafes/CapsulasGaribaldi/app/src/wasmJsMain/kotlin/com/example/capsulasgaribaldi/main.kt)
- Ensure it imports and calls the shared `CatalogoCafesScreen`.

### [Resources Migration]

#### [NEW] [composeResources/drawable](file:///F:/Projectes/Aplicacion_Cafes/CapsulasGaribaldi/app/src/commonMain/composeResources/drawable)
- Move images (`arabico.png`, `chocolate.png`, etc.) from `app/src/main/res/drawable` to this new directory.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:wasmJsBrowserDistribution` locally (if environment allows) to verify compilation.
- Run `./gradlew :app:assembleDebug` to verify Android build still works.

### Manual Verification
- Verify that images are displayed correctly in both Android and Web builds.
