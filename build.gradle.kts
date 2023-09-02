plugins {
    id("org.jetbrains.kotlin.jvm")
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}