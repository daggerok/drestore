plugins {
  idea
  base
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.ajoberstar.reckon")
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
}

val junit4Version: String by project
val assertjVersion: String by project
val coroutinesVersion: String by project
val junitJupiterVersion: String by project
val gradleWrapperVersion: String by project

idea {
  module.iml {
    beforeMerged(Action<org.gradle.plugins.ide.idea.model.Module> {
      dependencies.clear()
    })
  }
}

tasks {
  withType(Wrapper::class.java) {
    gradleVersion = gradleWrapperVersion
    distributionType = Wrapper.DistributionType.BIN
    // distributionType = Wrapper.DistributionType.ALL
  }
}

reckon {
  scopeFromProp()
  // stageFromProp()
  snapshotFromProp()
}

tasks {
  register("version") {
    println(project.version.toString())
  }
  register("status") {
    doLast {
      val status = grgit.status()
      status?.let { s ->
        println("workspace is clean: ${s.isClean}")
        if (!s.isClean) {
          if (s.unstaged.allChanges.isNotEmpty()) {
            println("""all unstaged changes: ${s.unstaged.allChanges.joinToString(separator = "") { i -> "\n - $i" }}""")
          }
        }
      }
    }
  }
}

defaultTasks("clean", "build")

allprojects {
  apply<JavaPlugin>()
  apply<io.spring.gradle.dependencymanagement.DependencyManagementPlugin>()

  sourceSets {
    main {
      java.srcDir("src/main/kotlin")
    }
    test {
      java.srcDir("src/test/kotlin")
    }
  }

  configurations {
    compileOnly {
      extendsFrom(configurations.annotationProcessor.get())
    }
  }

  repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
  }

  group = "daggerok"
  java.sourceCompatibility = JavaVersion.VERSION_1_8

  dependencies {
    implementation(enforcedPlatform("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"))
    testImplementation(enforcedPlatform("org.junit:junit-bom:$junitJupiterVersion"))
    // implementation(kotlin("stdlib")) // not needed. will be automatically bundled into jar...
    // implementation(kotlin("reflect"))
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // annotationProcessor("org.projectlombok:lombok")
    // compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")/* {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }*/
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:$junit4Version")
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "1.8"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform {
      includeEngines("junit-jupiter", "junit-vintage")
    }
    systemProperties["sleep"] = System.getProperty("sleep") ?: "10"
    testLogging {
      showCauses = true
      showExceptions = true
      showStackTraces = true
      showStandardStreams = true
      events(
          org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
      )
    }
  }
}
