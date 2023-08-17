plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

dependencies {
    implementation(project(":utilities"))
    implementation(project(":peepolab-modules:peepolab-common"))
    implementation("io.micronaut:micronaut-runtime")
}