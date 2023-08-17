plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

dependencies {
    implementation("org.gitlab4j:gitlab4j-api:6.0.0-rc.2")
    implementation("io.micronaut:micronaut-runtime")
}