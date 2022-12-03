import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
    id("org.jetbrains.dokka") version "1.7.20"
    `maven-publish`
}

var host = "github.com/TheFruxz/Stacked"

version = "3.1.2"
group = "de.fruxz"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {

    @Suppress("DependencyOnStdlib") implementation(kotlin("stdlib"))

    implementation("com.github.TheFruxz:Ascend:10.0.0")

    implementation("net.kyori:adventure-api:4.12.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.12.0")
    implementation("net.kyori:adventure-text-minimessage:4.12.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.12.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("io.ktor:ktor-client-core-jvm:2.1.3")

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
