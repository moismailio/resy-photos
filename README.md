# Resy Photos Project

its assessment project for Resy.

## Modules

- **App**: The main module which contains MainActivity and Application Class, considered as the
  application entry point.
- **build-logic**: Contains all the custom plugins that are used across the project.
- **Core:data**: Contains the required components that will be shared across all of the feature-data
  modules.
- **Core:domain**: Contains the required components that will be shared across all of the
  feature-domain modules.
- **Core:ui**: Contains base components that will be shared across all of the feature presentation
  modules.
- **design-system**: References all of the project standards for UI composable functions.
- **models**: Contains all of the models that will be shared across the app modules.
- **feature-x package**: Each feature package contains relevant modules such as: data, domain, di,
  presentation.

## Installation

To get started with Resy Photos, follow these instructions:

    ```bash
    git clone https://github.com/Mohamed-mekawy/resy-photos.git
    cd resy-photos
    ./gradlew build
    ```

in local.properties please add the host url as `HOST_URL`

## Stack

- **Kotlin**:
- **Jetpack Compose**
- **Dagger-Hilt**
- **Retrofit**
- **Coil**