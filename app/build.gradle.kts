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