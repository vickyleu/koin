import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core:koin-test"))
    api(libs.test.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.test.mockito)
}

tasks.withType<KotlinCompile>().all {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(libs.versions.jvmTarget.get()))
    }
}
java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get()) // or the desired Java version
    targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())// or the desired Java version
}
//val sourcesJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
//    archiveClassifier.set("sources")
//    from(sourceSets.main.map { it.allSource.sourceDirectories })
//}

//apply(from = file("../../gradle/publish-java.gradle.kts"))
