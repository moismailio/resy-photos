pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "photos"
include(":app")
include(":design-system")
include(":photo-details:presentation")
include(":models")
include(":photo-list:presentation")
include(":photo-list:domain")
include(":photo-list:data")
include(":photo-list:di")
include(":core:data")
include(":core:domain")
include(":core:ui")
