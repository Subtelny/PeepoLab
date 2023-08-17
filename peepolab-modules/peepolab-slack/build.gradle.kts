plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

dependencies {
    val kotlinVersion: String by project
    val slackBoltVersion: String by project

    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.19")
    implementation("com.slack.api:bolt:$slackBoltVersion")
//    implementation("com.slack.api:bolt-micronaut:$slackBoltVersion")
    implementation("com.slack.api:bolt-socket-mode:$slackBoltVersion")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-runtime")
}