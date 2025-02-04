package org.example.project.data.repository

import co.touchlab.kermit.Logger
import coil3.network.HttpException
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.withContext
import org.example.project.data.local.ToDoDao
import org.example.project.data.mappers.fromDTOToTodoItemEntityList
import org.example.project.data.mappers.fromEntityToTodoItemUI
import org.example.project.data.mappers.fromEntityToTodoItemUIList
import org.example.project.data.mappers.fromUItoLocalTodoItemEntity
import org.example.project.data.mappers.fromUItoTodoItemDTO
import org.example.project.data.remote.ApiService
import org.example.project.domain.model.TodoItemUI
import org.example.project.domain.repository.TodoRepository
import org.example.project.urils.DispatcherProvider
import org.koin.compose.error.UnknownKoinContext

class TodoRepositoryImpl(
       private val apiService: ApiService,
       private val todoDao: ToDoDao,
       private val dispatcherProvider: DispatcherProvider
) : TodoRepository {

    override suspend fun getAllTodos(): List<TodoItemUI> {
        return withContext(dispatcherProvider.io) {
            getAllTodosFromRemote()
            todoDao.getAllToDosItems().fromEntityToTodoItemUIList()
        }

    }

    override suspend fun getAllTodosFromLocalCache(): List<TodoItemUI> {
        return todoDao.getAllToDosItems().fromEntityToTodoItemUIList()
    }

    override suspend fun getAllTodosFromRemote() {
        return withContext(dispatcherProvider.io) {
            try {
                refreshCache()
            } catch (ex: Exception) {
                when (ex) {
                    is UnknownKoinContext, is ConnectTimeoutException, is HttpException -> {
                        Logger.e(ex) { "Error data remote" }
                        if (isCacheEmpty()) {
                            Logger.e(ex) { "No data local room" }
                            throw Exception("No data local room")

                        }
                    }

                    else -> {
                        throw ex
                    }
                }
            }
        }
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoItemUI? {
        return todoDao.getSingleToDoItemById(id = id)?.fromEntityToTodoItemUI()
    }

    override suspend fun addTodoItem(todo: TodoItemUI) {
        val newId = todoDao.addTodoItem(todo.fromUItoLocalTodoItemEntity())
        apiService.addTodo(
               id = newId.toString(),
               todo = todo.fromUItoTodoItemDTO().copy(id = newId.toInt())
        )
    }

    override suspend fun updateTodoItem(todo: TodoItemUI) {
        todoDao.addTodoItem(todo.fromUItoLocalTodoItemEntity())
        apiService.updateTodo(
               id = todo.id,
               todo = todo.fromUItoTodoItemDTO()
        )
    }

    override suspend fun deleteTodoItem(todo: TodoItemUI) {
        try {
            val response = apiService.deleteTodo(todo.id)
            if (response.status.value == 200) {
                Logger.d { "Success HTTP cloud delete" }
            } else {
                Logger.d { "Error HTTP cloud delete" }
            }
        } catch (ex: Exception) {
            when (ex) {
                is UnknownKoinContext, is ConnectTimeoutException, is HttpException -> {
                    Logger.e(ex) { "Error HTTP cloud not delete" }
                }

                else -> throw ex
            }

        }
    }

    private suspend fun isCacheEmpty(): Boolean {
        var empty = true
        if (todoDao.getAllToDosItems().isNotEmpty()) {
            empty = false
        }
        return empty
    }

    private suspend fun refreshCache() {
        val remoteTodos = apiService.getAllTodos().filterNotNull()
        todoDao.addAllTodoItems(remoteTodos.fromDTOToTodoItemEntityList())
    }
}
