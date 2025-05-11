plugins {
    id("org.springframework.boot") version "3.2.3"
    id("java")
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.6.4.jre11")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.mockito:mockito-core:5.10.0")
    testImplementation("com.microsoft.sqlserver:mssql-jdbc:12.6.4.jre11")
}

tasks.test {
    useJUnitPlatform()
}