import org.jetbrains.compose.compose

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "0.3.0-build150"
}

group = "app.nameplaceholder"
version = "1.0"

kotlin {
  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "14"
    }
  }
  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation(project(":common"))
        implementation(compose.desktop.currentOs)
      }
    }
    val jvmTest by getting
  }
}

compose.desktop {
  application {
    mainClass = "MainKt"
    jvmArgs.add("-Dsun.java2d.uiScale=2")
    nativeDistributions {
      packageName = "compose-sandbox"
    }
  }
}
