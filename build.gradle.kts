import java.util.*

plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    id("org.jetbrains.dokka") version "2.2.0"
    `maven-publish`
}

val publishVersion = System.getenv("GH_RELEASE_VERSION")
val calendar = Calendar.getInstance()

version = "$publishVersion-legacy" ?: "${calendar[Calendar.YEAR]}.${calendar[Calendar.MONTH] + 1}-dev"
group = "dev.fruxz"

repositories {
    mavenCentral()
    maven("https://nexus.fruxz.dev/repository/public/")
}

dependencies {

    testImplementation(kotlin("test"))

    implementation("dev.fruxz:ascend:2026.6-e59e51c")

    implementation("net.kyori:adventure-api:4.26.1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.26.1")
    implementation("net.kyori:adventure-text-minimessage:4.26.1")
    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")

}

publishing {

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven("https://nexus.fruxz.dev/repository/releases/") {
            name = "fruxz.dev"
            credentials {
                username = System.getenv("FXZ_NEXUS_USER")
                password = System.getenv("FXZ_NEXUS_SECRET")
            }
        }

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
    jvmToolchain(21)
}