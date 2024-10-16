plugins {
    id("java-library")
    kotlin("jvm")
}

java {
}

dependencies {
    implementation("javax.inject:javax.inject:1")
    implementation(kotlin("stdlib-jdk8"))
}
