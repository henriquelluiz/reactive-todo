package dev.henriquelluiz.todo.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class TaskHtmlController(val taskHandler: TaskHandler) {

    @Bean
    fun viewRoutes() = router {
        GET("/greeting", taskHandler::greeting)
    }
}

@Component
class TaskHandler {
    fun greeting(request: ServerRequest): Mono<ServerResponse> {
        val model = mutableMapOf<String, String>()
        model["message"] = "Hello, World!"
        return ServerResponse.ok()
            .contentType(TEXT_HTML)
            .render("hello", model)
    }
}