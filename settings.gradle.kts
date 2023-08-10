pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
    }
}

rootProject.name = "peepoLab"