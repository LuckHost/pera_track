plugins {
    id("java-library")
    kotlin("jvm")
}

java {
}

dependencies {
    implementation(libs.javax.inject)
    implementation(kotlin("stdlib-jdk8"))

    implementation(libs.rxjava)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}