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
    namespace = "org.koin.android"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    buildFeatures {
        buildConfig = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())

    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    api(project(":core:koin-core"))
    api(libs.android.appcompat)
    api(libs.android.activity)
    api(libs.android.fragment)
    api(libs.androidx.viewmodel)
    api(libs.androidx.commonJava8)

    // tests
    testImplementation(project(":core:koin-test"))
    testImplementation(project(":core:koin-test-junit4"))
    testImplementation(libs.kotlin.test)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito)
    testImplementation(libs.test.mockk)
}

//apply(from = file("../../gradle/publish-android.gradle.kts"))
