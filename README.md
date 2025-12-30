# HPoke 📱

HPoke is a modular Android Pokedex application built with modern Android technologies. It allows
users to browse and view detailed information about various Pokemon, leveraging
the [PokeAPI](https://pokeapi.co/).

## 🚀 Features

- **Home Screen**: A paginated list of Pokemon with search functionality.
- **Details Screen**: Detailed information about each Pokemon, including stats, types, and
  abilities.
- **Offline Support**: Local caching using Room database for seamless offline browsing.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3.

## 🛠 Tech Stack

- **Kotlin**: Primary programming language.
- **Jetpack Compose**: Modern toolkit for building native UI.
- **Koin**: Dependency injection framework.
- **Room**: Local data persistence.
- **Ktor**: Network client for API requests.
- **Paging 3**: For efficient loading of large datasets.
- **Landscapist (Coil)**: Image loading library for Compose.
- **Kotlinx Serialization**: For JSON parsing.
- **Coroutines & Flow**: For asynchronous programming.

## 🏗 Architecture

The project follows the **Clean Architecture** principles and is highly modularized to ensure
scalability and maintainability.

### Module Structure

- **`app`**: The entry point of the application, responsible for navigation and dependency injection
  setup.
- **`feature`**: Contains UI-related modules for specific features.
    - `:feature:home`: The home screen showing the list of Pokemon.
    - `:feature:details`: The detailed view for a selected Pokemon.
- **`core`**: Shared modules used across the app.
    - `:core:data`: Repositories and data source implementations.
    - `:core:database`: Room database definitions and DAOs.
    - `:core:network`: API client and network data models.
    - `:core:model`: Shared domain models.
    - `:core:ui`: Common UI components and design system tokens.
    - `:core:navigation`: Navigation logic and route definitions.
    - `:core:testing`: Test utilities and mock data.
    - `:core:designsystem`: Theme and design system definitions.
- **`build-logic`**: Custom Gradle plugins for consistent configuration across modules.

## 📸 Screenshots

*(Add screenshots here)*

## 🚦 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/HPoke.git
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Build and run the app on an emulator or a physical device.

## 📜 License

**HPoke** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.
