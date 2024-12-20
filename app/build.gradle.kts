plugins {
    id("application")
    id("checkstyle")
    id("com.adarshr.test-logger") version "3.2.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.48.0"
    id("io.freefair.lombok") version "8.6"
    kotlin("jvm") version "2.0.21"
    id("jacoco") // Добавлен плагин JaCoCo
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.5")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    options.release = 17
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

kotlin {
    jvmToolchain(21)
}

// Настройка JaCoCo
jacoco {
    toolVersion = "0.8.12" // Укажите версию JaCoCo
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Убедитесь, что задачи тестирования выполнены перед созданием отчета
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
