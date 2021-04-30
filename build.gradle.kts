apply(from = "repositories.gradle.kts")

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
}

plugins {
    java
    idea
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

idea {
    module {
        inheritOutputDirs = false
        outputDir = sourceSets.main.get().java.outputDir
        testOutputDir = sourceSets.test.get().java.outputDir
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.9")
    implementation("com.opencsv:opencsv:3.7")
    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.13.2")
}




