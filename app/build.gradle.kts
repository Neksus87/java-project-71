plugins {
    application
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"
application { mainClass.set("hexlet.code.App") }

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2") // Убедитесь, что только одна версия.
    implementation("info.picocli:picocli:4.6.1")

    // If using JUnit Jupiter
    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")

    // If using JUnit Vintage
    testCompileOnly ("junit:junit:4.13.2")
    testRuntimeOnly ("org.junit.vintage:junit-vintage-engine:5.9.2")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")

    // If using JUnit 4
    testImplementation ("junit:junit:4.13.2")

    // If using JUnit 3
    testCompileOnly ("junit:junit:3.8.2")
    testRuntimeOnly ("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}
tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

checkstyle {
    configFile = file("config/checkstyle/checkstyle.xml")
}