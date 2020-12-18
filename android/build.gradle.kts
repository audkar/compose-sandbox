plugins {
  id("org.jetbrains.compose") version "0.3.0-build135"
  id("com.android.application")
  kotlin("android")
}

group = "app.nameplaceholder"
version = "1.0"

repositories {
  google()
}

dependencies {
  implementation(project(":common"))
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.1")
}

android {
  compileSdkVersion(29)
  defaultConfig {
    applicationId = "app.nameplaceholder.android"
    minSdkVersion(24)
    targetSdkVersion(29)
    versionCode = 1
    versionName = "1.0"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    coreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}
