plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.google.devtools.ksp")
    id("io.micronaut.library")
}

dependencies {
    implementation(project(":peepolab-modules:peepolab-module-api"))
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation(project(":utilities"))


    val slackBoltVersion: String by project
    implementation("com.slack.api:bolt:$slackBoltVersion")
    implementation("com.slack.api:bolt-socket-mode:$slackBoltVersion")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.19")

    implementation("com.google.guava:guava:32.1.2-jre")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-http-client")
    testImplementation("org.assertj:assertj-core")
}