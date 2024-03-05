package dev.henriquelluiz.todo.model

import dev.henriquelluiz.todo.model.TaskStatus.TODO
import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.convert.EnumWriteSupport
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.LocalDateTime.now

enum class TaskStatus {
    TODO, DOING, DONE
}

class TaskStatusConverter : EnumWriteSupport<TaskStatus>()

@Table("task")
data class Task(
    var title: String,
    var description: String,
    var status: TaskStatus = TODO,
    var createdAt: LocalDateTime = now(),
    var finishedAt: LocalDateTime = now(),
    @Id var taskId: Long? = null
)

data class TaskRequest(
    val title: String,
    val description: String
)

fun TaskRequest.convert() = Task(title, description)