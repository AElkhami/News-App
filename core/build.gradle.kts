plugins {
    id("java-library")
    id("java-test-fixtures")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies{
    //Serialization
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.javax.inject)

    testFixturesImplementation(libs.kotlinx.coroutines.test)
    testFixturesImplementation(libs.junit)
}