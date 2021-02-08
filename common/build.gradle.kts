import org.jetbrains.compose.compose

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "0.3.0-build150"
  id("com.android.library")
  id("kotlin-android-extensions")
}

group = "app.nameplaceholder"
version = "1.0"

repositories {
  google()
}

kotlin {
  android {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
  }
  jvm("desktop") {
    compilations.all {
      kotlinOptions.jvmTarget = "14"
    }
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        api(compose.runtime)
        api(compose.foundation)
        api(compose.material)
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
      }
    }
    val commonTest by getting
    val androidMain by getting {
      dependencies {
        api("androidx.appcompat:appcompat:1.2.0")
        api("androidx.core:core-ktx:1.3.2")
      }
    }
    val androidTest by getting {
      dependencies {
        implementation("junit:junit:4.13.1")
      }
    }
    val desktopMain by getting
    val desktopTest by getting

    all {
      languageSettings.useExperimentalAnnotation("androidx.compose.material.ExperimentalMaterialApi")
    }
  }
}

android {
  compileSdkVersion(30)
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdkVersion(24)
    targetSdkVersion(30)
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.1")
}
