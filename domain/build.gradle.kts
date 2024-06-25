plugins {
    id("java")
}

group = "net.jfsanchez.hexdemo.domain"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.0")
}

tasks.test {
    useJUnitPlatform()
}