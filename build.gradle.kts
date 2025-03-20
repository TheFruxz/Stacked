plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.10"
    id("org.jetbrains.dokka") version "2.0.0"
    id("org.hildan.kotlin-publish") version "1.7.0"
    `maven-publish`
}

var host = "github.com/TheFruxz/Stacked"
val publishVersion = System.getenv("GH_RELEASE_VERSION")

version = "2025.1"
group = "dev.fruxz"

repositories {
    mavenCentral()
    maven("https://repo.fruxz.dev/releases")
}

dependencies {

    testImplementation(kotlin("test"))

    implementation("dev.fruxz:ascend:2024.2.2")

    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.17.0")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

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
                username = System.getenv("FRUXZ_DEV_USER")
                password = System.getenv("FRUXZ_DEV_SECRET")
            }
        }
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

tasks {

    compileKotlin {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
        }
    }

    dokkaHtml.configure {
        outputDirectory.set(layout.projectDirectory.dir("docs"))
    }

}

kotlin {
    jvmToolchain(23)
}