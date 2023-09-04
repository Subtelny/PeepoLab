plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
}

dependencies {
    implementation("io.micronaut:micronaut-inject:4.0.2")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
}