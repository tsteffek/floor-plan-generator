import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "0.2-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

repositories {
    mavenCentral()
}

configurations.forEach { it.exclude("org.slf4j", "slf4j-log4j12") }

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    /* Logging */
    implementation("org.slf4j:slf4j-simple:1.7.29")
    implementation("io.github.microutils:kotlin-logging:1.7.6")

    /* REST */
    implementation("com.sparkjava:spark-kotlin:1.0.0-alpha")

    /* CLI */
    implementation("com.github.ajalt:clikt:2.3.0")

    /* Reader */
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.7.3")

    /* Graph Output */
    implementation("com.github.sh0nk:matplotlib4j:0.4.0")

    /* Testing */
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

application {
    mainClassName = "bin/CLIKt"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "bin.CLIKt"
    }

    from {
        configurations.compile.collect {
            it.isDirectory() ? it : zipTree(it)
        }
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
