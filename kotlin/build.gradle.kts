import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.10"
}

group = "org.agile-grenoble"
version = "1.0.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("junit:junit:4.12")
	testImplementation("org.assertj:assertj-core:3.3.0")
}

tasks.test {
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
}

// config java extension to same target version, to avoid build failure on Gradle 8.x
java {
	targetCompatibility = JavaVersion.VERSION_1_8
}