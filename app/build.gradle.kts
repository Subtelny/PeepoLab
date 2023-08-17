plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
}

apply(from = "${rootDir}/dependencies.gradle")

dependencies {
    implementation(project(":peepolab-modules:peepolab-core"))
    implementation(project(":peepolab-modules:peepolab-common"))
    implementation(project(":peepolab-modules:peepolab-slack"))
    implementation(project(":peepolab-modules:peepolab-gitlab"))
}

application {
    mainClass = "pl.peepolab.app.ApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    compileTestKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("pl.peepolab.*")
    }
}