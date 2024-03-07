package dev.henriquelluiz.todo.service

import dev.henriquelluiz.todo.model.Task
import dev.henriquelluiz.todo.model.TaskRequest
import dev.henriquelluiz.todo.model.convert
import dev.henriquelluiz.todo.repository.TaskRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class TaskHandler(val taskRepository: TaskRepository) {
    suspend fun findTasksByStatus(request: ServerRequest): ServerResponse = coroutineScope {
        return@coroutineScope kotlin.runCatching {
            val status = request.queryParam("status").orElseThrow()
            taskRepository.findTasksByStatus("TODO")
        }.fold(
            onSuccess = { ok().contentType(APPLICATION_JSON).bodyAndAwait(it) },
            onFailure = {
                status(NOT_FOUND)
                    .contentType(APPLICATION_JSON)
                    .bodyAndAwait(flowOf(listOf<Task>()))
            }
        )
    }

    suspend fun createTask(request: ServerRequest): ServerResponse = coroutineScope {
        return@coroutineScope kotlin.runCatching {
            val task = request.awaitBody<TaskRequest>().convert()
            taskRepository.save(task)
        }.fold(
            onSuccess = {
                ok()
                    .contentType(APPLICATION_JSON)
                    .bodyAndAwait(flowOf("Task '${it.title}' created."))
            },
            onFailure = {
                status(INTERNAL_SERVER_ERROR)
                    .contentType(APPLICATION_JSON)
                    .bodyAndAwait(flowOf(it))
            }
        )
    }
}