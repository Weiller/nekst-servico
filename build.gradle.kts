import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.types.checker.NewKotlinTypeChecker

plugins {
	kotlin("plugin.jpa") version "1.3.31"
	id("org.springframework.boot") version "2.2.0.BUILD-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
	id("jacoco")
}

group = "br.com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
	testImplementation("org.springframework.security:spring-security-test")
    compile("ru.yandex.qatools.embed:postgresql-embedded:2.10")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

jacoco {
	toolVersion = "0.8.3"
	reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.jacocoTestReport {
	reports {
		xml.isEnabled = true
		csv.isEnabled = false
		html.destination = file("${buildDir}/jacocoHtml")
		xml.destination = file("${buildDir}/jacocoXml.xml")
	}
}

tasks.test {
	extensions.configure(JacocoTaskExtension::class) {
		file("$buildDir/jacoco/jacocoTest.exec")
	}
}

tasks.withType<JacocoReport> {
	afterEvaluate {
		classDirectories.setFrom(files(classDirectories.files.map {
			fileTree(it).apply {
				//exclude("br/com/nekstservico/service/**")
			}
		}))
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = BigDecimal(0.8)
			}
		}
	}
}

