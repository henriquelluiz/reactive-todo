package dev.henriquelluiz.todo.controller

import dev.henriquelluiz.todo.service.TaskHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class HttpController {

    @Bean
    fun taskRoute(handler: TaskHandler) = coRouter{
        "/api/tasks".nest {
            accept(APPLICATION_JSON).nest {
                GET("/{status}", handler::findTasksByStatus)
                POST("", handler::createTask)
            }
        }
    }
}