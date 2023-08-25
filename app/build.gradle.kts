plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.google.devtools.ksp")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    id("io.micronaut.test-resources")
    id("io.micronaut.aot")
}

apply(from = "${rootDir}/dependencies.gradle")

dependencies {
    implementation(project(":peepolab-modules:peepolab-module-core"))
    implementation(project(":peepolab-modules:peepolab-module-model"))
    implementation(project(":peepolab-modules:peepolab-module-api"))
    implementation(project(":peepolab-integrations:peepolab-integration-slack"))
    implementation(project(":peepolab-integrations:peepolab-integration-gitlab"))
}

application {
    mainClass = "pl.peepolab.app.ApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks {
    dockerBuild {
        images.add("peepolab-app:latest")
    }

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
        annotations("com.example.*")
    }
    testResources {
        additionalModules.add("jdbc-postgresql")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(false)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}
