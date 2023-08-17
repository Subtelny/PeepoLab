pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val micronautApplicationVersion: String by settings
        val gshadowVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        kotlin("kapt") version kotlinVersion apply false
        kotlin("plugin.allopen") version kotlinVersion apply false
        id("com.github.johnrengelman.shadow") version gshadowVersion apply false

        id("io.micronaut.application") version micronautApplicationVersion apply false
    }
}

rootProject.name = "peepoLab"
include("app")
include("utilities")
file("$rootDir/peepolab-modules")
    .listFiles { it -> it.isDirectory && it.name.startsWith("peepolab-")}
    ?.forEach { include(":peepolab-modules:${it.name}") }