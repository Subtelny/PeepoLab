import org.jetbrains.kotlin.incremental.createDirectory
import java.nio.file.Files
import java.nio.file.Paths

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("nu.studer.jooq")
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

val taskA = tasks.register("taskA") {
    doFirst {
        println("taskA")
    }
}

val taskB = tasks.register("taskB") {
    doFirst {
        println("taskB")
    }
}

subprojects {
    pluginManager.withPlugin("nu.studer.jooq") {
        jooq {
            configurations {
                create("main") {
                    generateSchemaSourceOnCompilation.set(true)

                    jooqConfiguration.apply {
                        generator.apply {
                            database.apply {
                                name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"

                                val rootPath =
                                    if (project.name == "app") "src/main/resources" else "build/tmp/modulesJooq/"

                                properties.addAll(
                                    arrayOf(
                                        org.jooq.meta.jaxb.Property().withKey("rootPath")
                                            .withValue(rootPath),
                                        org.jooq.meta.jaxb.Property().withKey("scripts")
                                            .withValue("db/liquibase-changelog.xml")
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        if (project.name != "app") {
            tasks.create("modulesJooq") {
                doFirst {
                    val dir = File(temporaryDir, "db")
                    if (dir.exists()) dir.deleteRecursively()
                    dir.createDirectory()
                    val file = File(dir, "liquibase-changelog.xml")
                    val liquibaseTemplate = Paths.get("${rootDir}/liquibase-changelog-template.xml")
                    Files.copy(liquibaseTemplate, file.toPath())
                }
            }

            tasks.named("generateJooq") {
                dependsOn("modulesJooq")
            }
        }
    }
}