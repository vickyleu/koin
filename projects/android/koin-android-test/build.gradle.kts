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
    namespace = "org.koin.android.test"
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
    implementation(kotlin("reflect"))
    implementation(project(":android:koin-android"))
    implementation(project(":android:koin-androidx-workmanager"))
    implementation(project(":core:koin-test"))
    // Test
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito)
}

//apply(from = file("../../gradle/publish-android.gradle.kts"))
