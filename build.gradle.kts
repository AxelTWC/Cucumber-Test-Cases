import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("kapt") version "1.5.10"
}

group = "seg3102-team3"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.itextpdf:itextpdf:5.0.6")
	implementation("org.apache.pdfbox:pdfbox:2.0.1")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.security:spring-security-test")
	implementation("org.mapstruct:mapstruct:1.5.0.Beta2")
	kapt("org.mapstruct:mapstruct-processor:1.5.0.Beta2")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.junit.platform:junit-platform-suite:1.8.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.cucumber:cucumber-java8:7.3.3")
	testImplementation("io.cucumber:cucumber-spring:7.3.3")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:7.3.3")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}

kapt {
	arguments {
		// Set Mapstruct Configuration options here
		// https://kotlinlang.org/docs/reference/kapt.html#annotation-processor-arguments
		// https://mapstruct.org/documentation/stable/reference/html/#configuration-options
		// arg("mapstruct.defaultComponentModel", "spring")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}