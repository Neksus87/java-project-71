plugins {
    id("application")
    id("checkstyle")
    // Удален плагин JaCoCo
    id("com.adarshr.test-logger") version "3.2.0"

    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.48.0"

    id("io.freefair.lombok") version "8.6"
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

    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    // Удален JaCoCo
    //testImplementation("org.jacoco:org.jacoco.agent:0.8.12")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
}

//tasks.withType<Test>() {
//    finalizedBy(tasks.jacocoTestReport) // Удалены все упоминания о JaCoCo
//    useJUnitPlatform()
//    testLogging {
//        showStandardStreams = true
//    }
//}

tasks.compileJava {
    options.release = 17
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

// Удалены настройки report'ов для JaCoCo
//tasks.jacocoTestReport {
//    reports {
//        dependsOn(tasks.test)
//        xml.required.set(true)
//    }
//}
