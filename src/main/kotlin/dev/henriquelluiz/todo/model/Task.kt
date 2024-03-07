package dev.henriquelluiz.todo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.LocalDateTime.now

enum class Status {
    TODO, DOING, DONE
}

@Table(
    schema = "todos",
    name = "task"
)
data class Task(
    var title: String,
    var description: String,
    var status: String,
    var createdAt: LocalDateTime,
    var finishedAt: LocalDateTime,
    @Id var taskId: Long? = null,
)

data class TaskRequest(
    val title: String,
    val description: String,
)

fun TaskRequest.convert() = Task(
    title,
    description,
    Status.TODO.toString(),
    now(),
    now()
)