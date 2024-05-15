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
    namespace = "org.koin.androidx.navigation"
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildFeatures {
        buildConfig = false
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
    implementation(project(":android:koin-android"))
    api(libs.androidx.navigation)
}

//apply(from = file("../../gradle/publish-android.gradle.kts"))
