package org.example.project.domain.usecase

import org.example.project.domain.model.TodoItemUI
import org.example.project.domain.repository.TodoRepository
import org.example.project.ui.pages.SortingDirection
import org.example.project.ui.pages.TodoItemOrder

class TodoUseCase(
       private val todoRepository: TodoRepository
) {

    suspend fun addTodoItem(todo: TodoItemUI) {
        if (todo.title.isBlank() || todo.description.isBlank()) {
            throw InvalidTodoException("Title and description cannot be empty")
        }
        todoRepository.addTodoItem(todo)
    }

    suspend fun update(todo: TodoItemUI) {
        if (todo.title.isBlank() || todo.description.isBlank()) {
            throw InvalidTodoException("Title and description cannot be empty")
        }
        todoRepository.updateTodoItem(todo)
    }

    suspend fun deleteTodoItem(todo: TodoItemUI) {
        todoRepository.deleteTodoItem(todo)
    }

    suspend fun toggleCompleteTodoItem(todo: TodoItemUI) {
        todoRepository.updateTodoItem(todo.copy(completes = !todo.completes))
    }

    suspend fun toggleArchiveTodoItem(todo: TodoItemUI) {
        todoRepository.updateTodoItem(todo.copy(archived = !todo.archived))
    }

    suspend fun getTodoItemById(id: Int): TodoItemUI? {
        return todoRepository.getSingleTodoItemById(id = id)
    }

    suspend fun getTodoItem(
           todoItemOrder: TodoItemOrder = TodoItemOrder.Time(SortingDirection.Down, true)
    ): TodoUseCaseResult {
        var todos = todoRepository.getAllTodosFromLocalCache()
        if (todos.isEmpty()) {
            todos = todoRepository.getAllTodos()
        }
        val filteredTodos = if (todoItemOrder.showArchived) {
            todos
        } else {
            todos.filter { !it.archived }
        }

        return when (todoItemOrder.sortingDirection) {
            SortingDirection.Down -> {
                when (todoItemOrder) {
                    is TodoItemOrder.Completed -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.completes })
                    is TodoItemOrder.Time -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.timestamp })
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredTodos.sortedBy { it.title.lowercase() })
                }
            }

            SortingDirection.Up -> {
                when (todoItemOrder) {
                    is TodoItemOrder.Completed -> TodoUseCaseResult.Success(filteredTodos.sortedByDescending { it.completes })
                    is TodoItemOrder.Time -> TodoUseCaseResult.Success(filteredTodos.sortedByDescending { it.timestamp })
                    is TodoItemOrder.Title -> TodoUseCaseResult.Success(filteredTodos.sortedByDescending { it.title.lowercase() })
                }
            }
        }

    }
}

sealed class TodoUseCaseResult {
    data class Success(val todoItems: List<TodoItemUI>) : TodoUseCaseResult()
    data class Error(val message: String) : TodoUseCaseResult()
}

class InvalidTodoException(message: String) : Exception(message)
