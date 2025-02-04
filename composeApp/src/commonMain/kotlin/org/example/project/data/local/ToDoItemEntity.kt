package org.example.project.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class ToDoItemEntity(
       @PrimaryKey(autoGenerate = true)
       val id: Int = 0,
       val title: String,
       val description: String,
       val timestamp: Int,
       val completes: Boolean,
       val archived: Boolean,
)
