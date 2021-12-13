import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.qodana")
    id("maven-publish")
}

var host = "github.com/TheFruxz/JET"

repositories {

    maven("https://papermc.io/repo/repository/maven-public/") // PaperMC
    maven("https://mvn.intellectualsites.com/content/repositories/thirdparty/")

}

dependencies {
    implementation(project(":JET-JVM")) // Internal
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT") // PaperMC

    // Kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

    // External
    implementation("org.ktorm:ktorm-core:3.4.1") // KTorm
    compileOnly("com.arcaniax:HeadDatabase-API:1.3.1") // Head-Database

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

java {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
}

tasks.processResources {
    expand("version" to project.version, "name" to project.name, "website" to "https://$host")
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

    publications.create("JET-Paper", MavenPublication::class) {
        from(components["kotlin"])
        artifactId = "jet-paper"
        version = version.toLowerCase()
    }
}

tasks.shadowJar {
    archiveClassifier.set("Runnable")
}

tasks.test {
    useJUnitPlatform()
}