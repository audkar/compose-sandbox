import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel.CURRENT

buildscript {
  repositories {
    gradlePluginPortal()
    jcenter()
    google()
    mavenCentral()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    classpath("com.android.tools.build:gradle:4.1.2")
  }
}

plugins {
  id("com.github.ben-manes.versions") version "0.36.0"
}

group = "app.nameplaceholder"
version = "1.0"

allprojects {
  repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    exclusiveContent {
      forRepository {
        maven { url = uri("https://kotlin.bintray.com/kotlinx/") }
      }
      filter { includeModuleByRegex("org.jetbrains.kotlinx", "kotlinx-datetime.*") }
    }
  }
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
  fun isNonStable(version: String): Boolean {
    val stableKeyword =
      listOf("FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
  }
  resolutionStrategy {
    componentSelection {
      all {
        if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
          reject("Release candidate")
        }
      }
    }
  }
  checkForGradleUpdate = true
  gradleReleaseChannel = CURRENT.id
}

tasks.wrapper {
  gradleVersion = "6.8.2"
  distributionType = Wrapper.DistributionType.ALL
}
