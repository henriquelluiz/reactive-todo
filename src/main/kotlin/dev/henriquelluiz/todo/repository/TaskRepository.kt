package dev.henriquelluiz.todo.repository

import dev.henriquelluiz.todo.model.Task
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CoroutineCrudRepository<Task, Long> {
    fun findTasksByStatus(status: String): Flow<Task>
}