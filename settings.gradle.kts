pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val kspVersion: String by settings
        val micronautVersion: String by settings
        val gshadowVersion: String by settings

        id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion apply false
        id("com.google.devtools.ksp") version kspVersion apply false
        id("com.github.johnrengelman.shadow") version gshadowVersion apply false
        id("io.micronaut.application") version micronautVersion apply false
        id("io.micronaut.test-resources") version micronautVersion apply false
        id("io.micronaut.aot") version micronautVersion apply false
        id("io.micronaut.library") version micronautVersion apply false
        id("nu.studer.jooq") version "8.2.1" apply false
    }
}


rootProject.name = "peepoLab"
include("app")
include("utilities")
file("$rootDir/peepolab-modules")
    .listFiles { it -> it.isDirectory && it.name.startsWith("peepolab-")}
    ?.forEach { include(":peepolab-modules:${it.name}") }
file("$rootDir/peepolab-integrations")
    .listFiles { it -> it.isDirectory && it.name.startsWith("peepolab-")}
    ?.forEach { include(":peepolab-integrations:${it.name}") }