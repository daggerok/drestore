pluginManagement {
  repositories {
    gradlePluginPortal()
    maven(url = uri("https://repo.spring.io/milestone"))
    mavenCentral()
  }
  val kotlinVersion: String by extra
  val reckonVersion: String by extra
  val springBootVersion: String by extra
  val dependencyManagementVersion: String by extra
  val versionsGradlePluginVersion: String by extra
  plugins {
    idea
    base
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.ajoberstar.reckon") version reckonVersion
    id("org.springframework.boot") version springBootVersion
    id("com.github.ben-manes.versions") version versionsGradlePluginVersion
    id("io.spring.dependency-management") version dependencyManagementVersion
  }
}

val name: String by extra
rootProject.name = name

include(
    "drestore"
)
