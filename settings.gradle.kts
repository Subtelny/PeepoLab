pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val kspVersion: String by settings
        val gshadowVersion: String by settings
        val micronautVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        kotlin("kapt") version kotlinVersion apply false
        kotlin("plugin.allopen") version kotlinVersion apply false

        id("com.github.johnrengelman.shadow") version gshadowVersion apply false
        id("io.micronaut.application") version "3.7.10" apply false
    }
}

rootProject.name = "peepoLab"
include("app")
