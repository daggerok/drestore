plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.springframework.boot")
}

tasks {
  bootJar {
    launchScript()
  }
}
