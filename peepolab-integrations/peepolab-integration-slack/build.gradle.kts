plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.google.devtools.ksp")
    id("io.micronaut.library")
    id("nu.studer.jooq")
}

dependencies {
    val slackBoltVersion: String by project

    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.19")
    implementation("com.slack.api:bolt:$slackBoltVersion")
    implementation("com.slack.api:bolt-socket-mode:$slackBoltVersion")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-http-client")
    implementation(project(":peepolab-modules:peepolab-module-api"))
    jooqGenerator("org.jooq:jooq-meta-extensions-liquibase")
}