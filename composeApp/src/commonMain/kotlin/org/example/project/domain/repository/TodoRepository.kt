package org.example.project.domain.repository

import org.example.project.domain.model.TodoItemUI

interface TodoRepository {

    suspend fun getAllTodos(): List<TodoItemUI>
    suspend fun getAllTodosFromLocalCache(): List<TodoItemUI>
    suspend fun getAllTodosFromRemote()
    suspend fun getSingleTodoItemById(id: Int): TodoItemUI?
    suspend fun addTodoItem(todo: TodoItemUI)
    suspend fun updateTodoItem(todo: TodoItemUI)
    suspend fun deleteTodoItem(todo: TodoItemUI)
}
