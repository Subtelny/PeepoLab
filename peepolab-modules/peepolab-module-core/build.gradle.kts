plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.google.devtools.ksp")
    id("io.micronaut.library")
}

dependencies {
    implementation(project(":utilities"))
    implementation(project(":peepolab-modules:peepolab-module-api"))
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation("io.micronaut:micronaut-http-client")
}