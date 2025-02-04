package org.example.project.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_items")
    suspend fun getAllToDosItems(): List<ToDoItemEntity>

    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getSingleToDoItemById(id: Int): ToDoItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTodoItems(todos: List<ToDoItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoItem(todo: ToDoItemEntity):Long

    @Delete
    suspend fun deleteTodoItem(todo: ToDoItemEntity)
}
