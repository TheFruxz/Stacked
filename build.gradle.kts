import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jetbrains.dokka") version "1.9.20"
    `maven-publish`
}

var host = "github.com/TheFruxz/Stacked"

version = "2024.2"
group = "dev.fruxz"

repositories {
    mavenCentral()
    maven("https://repo.fruxz.dev/releases")
}

dependencies {

    testImplementation(kotlin("test"))

    implementation("dev.fruxz:ascend:2024.1.2")

    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.17.0")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

}

val dokkaJavadocJar by tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

val dokkaHtmlJar by tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-doc")
}

val sourceJar by tasks.register<Jar>("sourceJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {

    repositories {
        mavenLocal()
        maven("https://repo.fruxz.dev/releases") {
            name = "fruxz.dev"
            credentials {
                username = project.findProperty("fruxz.dev.user") as? String? ?: System.getenv("FRUXZ_DEV_USER")
                password = project.findProperty("fruxz.dev.secret") as? String? ?: System.getenv("FRUXZ_DEV_SECRET")
            }
        }
    }

    publications.create("Stacked", MavenPublication::class) {
        artifactId = name.lowercase()
        version = version.lowercase()

        artifact(dokkaJavadocJar)
        artifact(dokkaHtmlJar)
        artifact(sourceJar) {
            classifier = "sources"
        }

        from(components["kotlin"])

    }

}

tasks {

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-receivers")
        }
    }

    dokkaHtml.configure {
        outputDirectory.set(layout.projectDirectory.dir("docs"))
    }

}

kotlin {
    jvmToolchain(21)
}

java {
    withJavadocJar()
    withSourcesJar()
}