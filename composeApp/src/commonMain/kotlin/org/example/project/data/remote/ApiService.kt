package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.example.project.data.dto.TodoItemItemDTO

class ApiService(
       private val client: HttpClient
) {

    suspend fun getAllTodos(): List<TodoItemItemDTO> {
        return client.get {
            url("todos.json") // Относительный путь (полный URL будет BASE_URL + "register")
        }.body()
    }

    suspend fun getSingleTodoByID(id: Int): Map<String, TodoItemItemDTO> {
        return client.get {
            url("todos.json")
            parameter("orderBy", "\"ID\"")
            parameter("equalTo", id)
        }.body()
    }

    suspend fun addTodo(
           id: String,
           todo: TodoItemItemDTO
    ): HttpResponse {
        return client.put {
            url("todos/$id.json")
            setBody(todo)
        }

    }

    suspend fun deleteTodo(id: Int): HttpResponse {
        return client.delete {
            url("todos/$id.json")
           // setBody(id)
        }

    }

    suspend fun updateTodo(
           id: Int,
           todo: TodoItemItemDTO
    ): HttpResponse {
        return client.put {
            url("todos/$id.json")
            setBody(todo)
        }
    }

}
