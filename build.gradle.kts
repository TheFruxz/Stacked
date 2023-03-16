import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jetbrains.dokka") version "1.8.10"
    `maven-publish`
}

var host = "github.com/TheFruxz/Stacked"

version = "4.0.0"
group = "de.fruxz"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {

    @Suppress("DependencyOnStdlib") implementation(kotlin("stdlib"))

    implementation("com.github.TheFruxz:Ascend:2023.1")

    implementation("net.kyori:adventure-api:4.13.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.13.0")
    implementation("net.kyori:adventure-text-minimessage:4.13.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.13.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.ktor:ktor-client-core-jvm:2.2.4")

}

java {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
    withJavadocJar()
    withSourcesJar()
}

tasks {

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    dokkaHtml.configure {
        outputDirectory.set(buildDir.resolve("../docs/"))
    }

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

val source by tasks.register<Jar>("sourceJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {

    repositories {

        mavenLocal()

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.$host")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }

    }

    publications.create("Stacked", MavenPublication::class) {

        from(components["kotlin"])

        artifact(dokkaJavadocJar)
        artifact(dokkaHtmlJar)
        artifact(source)

        artifactId = "stacked"
        version = version.toLowerCase()

    }

}
