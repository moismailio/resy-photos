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
include(":photo-details")
include(":models")
include(":photo-list:photo-list-presentation")
include(":photo-list:photo-list-domain")
include(":photo-list:photo-list-data")
include(":core:data")
include(":core:domain")
