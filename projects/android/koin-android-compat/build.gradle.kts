plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmTarget.get()))
    }
}

android {

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "org.koin.android.compat"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildFeatures {
        buildConfig = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    api(project(":android:koin-android"))
}

// android sources
val sourcesJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.map { it.java.srcDirs })
}

//apply(from = file("../../gradle/publish-android.gradle.kts"))
