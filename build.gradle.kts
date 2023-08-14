plugins {
    kotlin("jvm") apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
