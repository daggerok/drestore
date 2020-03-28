package daggerok.drestore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DrestoreApplication

fun main(args: Array<String>) {
  runApplication<DrestoreApplication>(*args)
}
