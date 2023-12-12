pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven {setUrl("https://jitpack.io")}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {setUrl("https://jitpack.io")}
    }
}

rootProject.name = "Dota2-Music"
include(":app")
include(":player")
