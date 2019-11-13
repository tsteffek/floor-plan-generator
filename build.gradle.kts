import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    /* Logging */
    implementation("org.slf4j:slf4j-simple:1.7.29")
    implementation("io.github.microutils:kotlin-logging:1.7.6")

    /* REST */
    implementation("com.sparkjava:spark-kotlin:1.0.0-alpha")

    /* Reader */
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.7.3")

    /* Graph Output */
    implementation("com.github.sh0nk:matplotlib4j:0.4.0")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
