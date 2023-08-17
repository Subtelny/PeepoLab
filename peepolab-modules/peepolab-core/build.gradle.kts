plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

dependencies {
    implementation(project(":utilities"))
}