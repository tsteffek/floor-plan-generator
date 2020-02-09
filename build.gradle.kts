import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.startScripts
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.3.50"
    application
    jacoco
    id("org.jetbrains.dokka") version "0.10.0"
}

repositories {
    mavenCentral()
    jcenter()
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
    testImplementation("io.mockk:mockk:1.9.3")
}

application {
    mainClassName = "bin/CLIKt"
}

tasks {
    startScripts {
        applicationName = "fpg"
    }

    register<DokkaTask>("htmlDokka") {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
        configuration {
            includeNonPublic = false
            skipDeprecated = true
            skipEmptyPackages = true
            jdkVersion = 8
        }
    }

    val dokka by getting(DokkaTask::class) {
        outputFormat = "gfm"
        outputDirectory = "docs"
        configuration {
            moduleName = "docs"
            includeNonPublic = false
            skipDeprecated = true
            skipEmptyPackages = true
            jdkVersion = 8
        }

        doLast { // move the docs from ./docs/docs to ./docs
            ant.withGroovyBuilder {
                "move"("file" to "./docs/docs", "todir" to ".")
            }
        }
    }

    jar {
        dependsOn(dokka, "htmlDokka")
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = false
        }
    }

    val test by getting(Test::class) {
        useJUnitPlatform { }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
