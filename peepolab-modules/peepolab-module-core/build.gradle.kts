plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.google.devtools.ksp")
    id("io.micronaut.library")
}

dependencies {
    implementation(project(":utilities"))
    implementation(project(":jooq-generated-tables"))
    implementation(project(":peepolab-modules:peepolab-module-api"))
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("io.micronaut:micronaut-http-client")
    implementation("org.jooq:jooq")
}