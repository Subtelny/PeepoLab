import org.jetbrains.kotlin.incremental.createDirectory
import org.jetbrains.kotlin.utils.addToStdlib.ifTrue
import java.nio.file.Files
import java.nio.file.Paths

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("nu.studer.jooq")
}

dependencies {
    jooqGenerator("org.jooq:jooq-meta-extensions-liquibase")
}

jooq {
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"

                        val rootPath =
                            if (project.name == "jooq-generated-tables")
                                "src/main/resources"
                            else
                                "build/tmp/generateLiquibaseChangelog/"

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

tasks.create("generateLiquibaseChangelog") {
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

tasks.named("generateJooq") {
    dependsOn("generateLiquibaseChangelog")
}