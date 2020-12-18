import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "0.3.0-build135"
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
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.AppImage)
      packageName = "compose-sandbox"
    }
  }
}
