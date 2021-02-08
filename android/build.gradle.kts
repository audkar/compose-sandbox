plugins {
  id("org.jetbrains.compose") version "0.3.0-build150"
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
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
}

android {
  compileSdkVersion(30)
  defaultConfig {
    applicationId = "app.nameplaceholder.compose.android"
    minSdkVersion(24)
    targetSdkVersion(30)
    versionCode = 1
    versionName = "1.0"
    multiDexEnabled = true
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      signingConfig = signingConfigs.getByName("debug")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

val copyDist = tasks.register<Copy>("copyApkForDistribution") {
  dependsOn("assembleRelease")
  val packageFiles = tasks.getByName("packageRelease").outputs.files
  inputs.files(packageFiles)
  val apkFile = packageFiles.asFileTree.matching { include("*.apk") }
  from(apkFile)
  rename {
    "${rootProject.name}.apk"
  }
  into("$buildDir/apkDistrib/")
}

tasks.register("uploadGDrive") {
  dependsOn(copyDist)
  val packageFiles = tasks.getByName(copyDist.name).outputs.files
  inputs.files(packageFiles)

  doLast {
    val apkFile = packageFiles.asFileTree.matching { include("*.apk") }.singleFile
    exec {
      commandLine("rclone", "copy", apkFile.absolutePath, "mygdrive:rclone")
    }
    println("copied = ${apkFile.absolutePath}")
  }
}
