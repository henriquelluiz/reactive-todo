package dev.henriquelluiz.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveTodoApplication

fun main(args: Array<String>) {
    runApplication<ReactiveTodoApplication>(*args)
}
