# Gradle Convention Plugin Structure

## Project Layout

```
HPoke/
├── .gitignore
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── app/
│   └── build.gradle.kts
└── build-logic/
    ├── .gitignore
    ├── settings.gradle.kts
    └── convention/
        ├── .gitignore
        ├── build.gradle.kts
        └── src/main/kotlin/com/example/hpoke/convention/
            ├── AndroidApplicationConventionPlugin.kt
            ├── AndroidComposeConventionPlugin.kt
            └── KotlinAndroidConventionPlugin.kt
```

## Convention Module Structure

The `build-logic/convention` module is a separate Gradle module that:

1. **Applies the kotlin-dsl plugin** - Allows writing plugins in Kotlin DSL
2. **Defines convention plugins** - Registered in `build.gradle.kts`
3. **Provides reusable configurations** - For Android projects

### Available Convention Plugins

#### 1. `hpoke.android.application`
- Applies the Android Application plugin
- Configures compile SDK (36), min SDK (24), target SDK (36)
- Sets Java compatibility level to 11

#### 2. `hpoke.android.application.compose`
- Enables Jetpack Compose
- Configures Compose build features
- Sets Kotlin compiler extension version

### Plugin Aliases in libs.versions.toml

```toml
[plugins]
hpoke-android-application = { id = "hpoke.android.application" }
hpoke-android-application-compose = { id = "hpoke.android.application.compose" }
```

## Usage in app/build.gradle.kts

```kotlin
plugins {
    alias(libs.plugins.hpoke.android.application)
    alias(libs.plugins.hpoke.android.application.compose)
}
```

## Git Ignore Strategy

- **Root `.gitignore`**: Covers all Android/Gradle build artifacts
- **`build-logic/.gitignore`**: Covers build-logic module specific files
- **`build-logic/convention/.gitignore`**: Covers convention module specific files

## Benefits

✅ **Modular structure** - Convention plugins isolated in separate module  
✅ **Clean separation** - Build logic separate from app logic  
✅ **Scalable** - Easy to add more convention plugins  
✅ **Reusable** - Plugins can be applied to any module  
✅ **Maintainable** - Centralized configuration management  

