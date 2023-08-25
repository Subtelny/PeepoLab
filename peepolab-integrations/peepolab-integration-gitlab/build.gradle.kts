plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
    id("io.micronaut.library") version "4.0.2"
}

dependencies {
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation(project(":peepolab-modules:peepolab-module-api"))
    implementation(project(":utilities"))
    implementation("org.gitlab4j:gitlab4j-api:6.0.0-rc.2")
    implementation("io.micronaut:micronaut-http-client")
}