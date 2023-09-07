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
    jooqGenerator("org.testcontainers:testcontainers:1.19.0")
    jooqGenerator("org.testcontainers:postgresql:1.19.0")
    jooqGenerator("ch.qos.logback:logback-core:1.4.8")
    jooqGenerator("ch.qos.logback:logback-classic:1.4.8")
    jooqGenerator("org.postgresql:postgresql:42.6.0")
    jooqGenerator("org.jooq:jooq-meta-extensions-liquibase")

    liquibaseRuntime("org.testcontainers:testcontainers:1.19.0")
    liquibaseRuntime("org.testcontainers:postgresql:1.19.0")
    liquibaseRuntime("ch.qos.logback:logback-core:1.4.8")
    liquibaseRuntime("org.liquibase:liquibase-core:4.23.1")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.4.8")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
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

val postgresContainer = org.testcontainers.containers.PostgreSQLContainer<Nothing>("postgres:15.4").apply {
    withDatabaseName("liquibase")
    withUsername("user")
    withPassword("pass")
    withConnectTimeoutSeconds(30)
}

tasks {
    register("updateLiquibaseConfiguration") {
        dependsOn("startPostgres")
        doFirst {
            liquibase {
                activities {
                    get("main").apply {
                        arguments = mapOf(
                            "changelogFile" to "src/main/resources/db/liquibase-changelog.xml",
                            "url" to postgresContainer.jdbcUrl,
                            "username" to postgresContainer.username,
                            "password" to postgresContainer.password,
                            "searchPath" to project.projectDir.path,
                        )
                    }
                }
            }
        }
    }

    register("updateJooqConfiguration") {
        dependsOn("startPostgres")
        doFirst {
            jooq {
                configurations {
                    get("main").apply {
                        jooqConfiguration.apply {
                            jdbc.apply {
                                url = postgresContainer.jdbcUrl
                                username = postgresContainer.username
                                password = postgresContainer.password
                            }
                        }
                    }
                }
            }
        }
    }

    register("generateLiquibaseChangelog") {
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

    register("startPostgres") {
        doLast {
            if (!postgresContainer.isRunning) {
                postgresContainer.start()
                println("Container started with JDBC URL: ${postgresContainer.jdbcUrl}")
                println("Username: ${postgresContainer.username}")
                println("Password: ${postgresContainer.password}")
            }
        }
        finalizedBy("stopPostgres")
    }

    register("stopPostgres") {
        doLast {
            if (postgresContainer.isRunning) {
                postgresContainer.stop()
            }
        }
    }

    named("generateJooq") {
        dependsOn("startPostgres", "update", "updateJooqConfiguration")
        finalizedBy("stopPostgres")
    }

    named("update") {
        dependsOn("startPostgres", "generateLiquibaseChangelog", "updateLiquibaseConfiguration")
        finalizedBy("stopPostgres")
    }
}