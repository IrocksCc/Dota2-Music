pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven {setUrl("https://jitpack.io")}
        maven {setUrl("https://repo1.maven.org/maven2/")}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {setUrl("https://jitpack.io")}
        maven {setUrl("https://repo1.maven.org/maven2/")}
    }
}

rootProject.name = "Dota2-Music"
include(":app")
include(":player")
include(":tools")
