/***
 *
 * To jest bardzo ciekawa sklejka, ważne że działa :D
 *
 */

import org.jetbrains.kotlin.incremental.createDirectory
import java.nio.file.Files
import java.nio.file.Paths

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("nu.studer.jooq")
    id("org.liquibase.gradle") version "2.2.0"
}

dependencies {
    jooqGenerator("org.jooq:jooq-meta-extensions-liquibase")
    jooqGenerator("org.testcontainers:testcontainers:1.19.0")
    jooqGenerator("org.testcontainers:postgresql:1.19.0")
    jooqGenerator("ch.qos.logback:logback-core:1.4.8")
    jooqGenerator("ch.qos.logback:logback-classic:1.4.8")
    jooqGenerator("org.testcontainers:postgresql:1.19.0")
    jooqGenerator("org.postgresql:postgresql:42.6.0")

    liquibaseRuntime("org.liquibase:liquibase-core:4.23.1")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
    liquibaseRuntime("ch.qos.logback:logback-core:1.4.8")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.4.8")
    liquibaseRuntime("org.testcontainers:testcontainers:1.19.0")
    liquibaseRuntime("org.testcontainers:postgresql:1.19.0")

    implementation("org.testcontainers:testcontainers:1.19.0")
    implementation("org.testcontainers:postgresql:1.19.0")
}

buildscript {
    dependencies {
        classpath("org.testcontainers:postgresql:1.19.0")
    }
}

liquibase {
    activities {
        register("main") {}
    }
    runList = "main"
}

jooq {
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                generator.apply {
                    database.apply {
                        inputSchema = "public"
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                    }
                }
            }
        }
    }
}

tasks.named("build") {
    dependsOn("initializeTableGeneration")
}

tasks.named("generateJooq") {
    onlyIf { container.isRunning }
    dependsOn("update")
    doLast {
        container.stop()
    }
}

tasks.named("update") {
    onlyIf { container.isRunning }
    dependsOn("generateLiquibaseChangelog")
}

tasks.register("updateLiquibaseConfiguration") {
    doFirst {
        project.liquibase.activities["main"]!!.arguments = mapOf(
            "changelogFile" to "src/main/resources/db/liquibase-changelog.xml",
            "url" to container.jdbcUrl,
            "username" to container.username,
            "password" to container.password,
            "searchPath" to project.projectDir.path,
        )
    }
}

tasks.register("updateJooqConfiguration") {
    doFirst {
        project.jooq.configurations["main"].jooqConfiguration.jdbc.apply {
            url = container.jdbcUrl
            username = container.username
            password = container.password
        }
    }
}

tasks.register("initializeTableGeneration") {
    dependsOn("startPostgres")
    finalizedBy("runTableGeneration")
}

tasks.register("runTableGeneration") {
    dependsOn("updateLiquibaseConfiguration", "updateJooqConfiguration")
    finalizedBy("update", "generateJooq")
}

tasks.register("generateLiquibaseChangelog") {
    doFirst {
        if (temporaryDir.exists()) {
            temporaryDir.deleteRecursively()
        }
        val dir = File(temporaryDir, "db/changelog")
        dir.createDirectory()

        rootProject.subprojects.asSequence()
            .filter { project.name != it.name }
            .filter { it.subprojects.isEmpty() }
            .map { File(it.projectDir, "src/main/resources/db/changelog") }
            .filter { it.exists() }
            .map { it.listFiles()!! }
            .forEach {
                it.forEach { file ->
                    val targetPath = Paths.get(dir.absolutePath, file.name)
                    Files.copy(file.toPath(), targetPath)
                }
            }
    }
}

val container = org.testcontainers.containers.PostgreSQLContainer<Nothing>("postgres:15.4").apply {
    withExposedPorts(5432)
    withDatabaseName("liquibase")
    withUsername("user")
    withPassword("pass")
    withConnectTimeoutSeconds(30)
}

val startPostgres: TaskProvider<Task> = tasks.register("startPostgres") {
    doLast {
        if (!container.isRunning) {
            container.start()
            println("Container started with JDBC URL: ${container.jdbcUrl}")
            println("Username: ${container.username}")
            println("Password: ${container.password}")
        }
    }
}