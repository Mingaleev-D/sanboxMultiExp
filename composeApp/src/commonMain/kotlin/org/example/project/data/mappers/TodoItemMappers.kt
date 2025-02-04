package org.example.project.data.mappers

import org.example.project.data.dto.TodoItemItemDTO
import org.example.project.data.local.ToDoItemEntity
import org.example.project.domain.model.TodoItemUI

fun TodoItemUI.fromUItoLocalTodoItemEntity(): ToDoItemEntity {
    return ToDoItemEntity(
           id = id,
           title = title,
           description = description,
           timestamp = timestamp,
           completes = completes,
           archived = archived
    )
}

fun List<TodoItemUI>.fromUItoLocalTodoItemEntityList(): List<ToDoItemEntity> {
    return map { it.fromUItoLocalTodoItemEntity() }
}

fun TodoItemUI.fromUItoTodoItemDTO(): TodoItemItemDTO {
    return TodoItemItemDTO(
           id = id,
           title = title,
           description = description,
           timestamp = timestamp,
           completes = completes,
           archived = archived
    )
}

fun List<TodoItemUI>.fromUItoTodoItemDTOList(): List<TodoItemItemDTO> {
    return map { it.fromUItoTodoItemDTO() }
}

fun ToDoItemEntity.fromEntityToTodoItemUI(): TodoItemUI {
    return TodoItemUI(
           id = id,
           title = title,
           description = description,
           timestamp = timestamp,
           completes = completes,
           archived = archived
    )
}

fun List<ToDoItemEntity>.fromEntityToTodoItemUIList(): List<TodoItemUI> {
    return map { it.fromEntityToTodoItemUI() }
}

fun ToDoItemEntity.fromEntityToTodoItemDTO(): TodoItemItemDTO {
    return TodoItemItemDTO(
           id = id,
           title = title,
           description = description,
           timestamp = timestamp,
           completes = completes,
           archived = archived
    )
}

fun List<ToDoItemEntity>.fromEntityToTodoItemDTOList(): List<TodoItemItemDTO> {
    return map { it.fromEntityToTodoItemDTO() }
}

fun TodoItemItemDTO.fromDTOToTodoItemEntity(): ToDoItemEntity {
    return ToDoItemEntity(
           id = id,
           title = title,
           description = description,
           timestamp = timestamp,
           completes = completes,
           archived = archived
    )
}

fun List<TodoItemItemDTO>.fromDTOToTodoItemEntityList(): List<ToDoItemEntity> {
    return map { it.fromDTOToTodoItemEntity() }
}
