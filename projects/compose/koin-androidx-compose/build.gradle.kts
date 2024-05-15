@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmTarget.get()))
    }
}
android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "org.koin.androidx.compose"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildFeatures {
        buildConfig = false
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = libs.versions.android.compose.compiler.get()
//    }
}

dependencies {
    api(project(":android:koin-android"))
    api(project(":compose:koin-compose"))
    api(libs.androidx.composeRuntime)
    api(libs.androidx.composeViewModel)
}

// android sources
val sourcesJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.map { it.java.srcDirs })
}

//apply(from = file("../../gradle/publish-android.gradle.kts"))
