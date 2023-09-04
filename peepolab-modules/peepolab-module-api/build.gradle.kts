plugins {
    kotlin("jvm")
}

dependencies{
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation(project(":utilities"))
    implementation("io.micronaut:micronaut-inject:4.0.2")
    implementation("org.jooq:jooq:3.18.5")
}