package org.example.project.ui.pages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.example.project.domain.model.TodoItemUI
import org.example.project.domain.usecase.TodoUseCase
import org.example.project.domain.usecase.TodoUseCaseResult
import org.example.project.urils.DispatcherProvider

class TodosViewModel(
       private val todoUseCase: TodoUseCase,
       private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _todosState = mutableStateOf(TodosListState())
    val todosState = _todosState
    private var undoTodoItem: TodoItemUI? = null
    private var getTodoItemJob: Job? = null
    private val errorHandle = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        todosState.value = todosState.value.copy(
               error = throwable.message,
               isLoading = false
        )
    }

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.Archive -> {
                viewModelScope.launch(dispatcherProvider.io + errorHandle) {
                    todoUseCase.toggleArchiveTodoItem(todo = event.todo)
                    getTodoItems()
                }
            }

            is TodoListEvent.Complete -> {
                viewModelScope.launch(dispatcherProvider.io + errorHandle) {
                    todoUseCase.toggleCompleteTodoItem(todo = event.todo)
                    getTodoItems()
                }
            }

            is TodoListEvent.Delete -> {
                viewModelScope.launch(dispatcherProvider.io + errorHandle) {
                    todoUseCase.deleteTodoItem(event.todo)
                    getTodoItems()
                    undoTodoItem = event.todo
                }
            }

            is TodoListEvent.Sort -> {
                val stateOrderAllReadyMatchesEventOrder = event.todoItemOrder::class == _todosState.value.todoItemOrder::class &&
                                                          event.todoItemOrder.showArchived == _todosState.value.todoItemOrder.showArchived &&
                                                          event.todoItemOrder.sortingDirection == _todosState.value.todoItemOrder.sortingDirection
                if (stateOrderAllReadyMatchesEventOrder) {
                    return
                }
                _todosState.value = _todosState.value.copy(
                       todoItemOrder = event.todoItemOrder
                )
                getTodoItems()
            }

            TodoListEvent.UndoDelete -> {
                viewModelScope.launch(dispatcherProvider.io + errorHandle) {
                    todoUseCase.addTodoItem(todo = undoTodoItem ?: return@launch)
                    undoTodoItem = null
                    getTodoItems()
                }
            }
        }

    }

    fun getTodoItems() {
        getTodoItemJob?.cancel()
        getTodoItemJob = viewModelScope.launch(dispatcherProvider.io + errorHandle) {
            val result = todoUseCase.getTodoItem(
                   todoItemOrder = _todosState.value.todoItemOrder
            )

            when (result) {
                is TodoUseCaseResult.Error -> {
                    _todosState.value = _todosState.value.copy(
                           error = result.message ?: "Unknown error",
                           isLoading = false
                    )
                }

                is TodoUseCaseResult.Success -> {
                    _todosState.value = _todosState.value.copy(
                           todoItems = result.todoItems,
                           todoItemOrder = _todosState.value.todoItemOrder,
                           isLoading = false
                    )
                }
            }
        }

    }
}

sealed class TodoListEvent {
    data class Sort(val todoItemOrder: TodoItemOrder) : TodoListEvent()
    data class Delete(val todo: TodoItemUI) : TodoListEvent()
    data class Complete(val todo: TodoItemUI) : TodoListEvent()
    data class Archive(val todo: TodoItemUI) : TodoListEvent()
    object UndoDelete : TodoListEvent()
}

data class TodosListState(
       val todoItems: List<TodoItemUI> = emptyList(),
       val todoItemOrder: TodoItemOrder = TodoItemOrder.Time(SortingDirection.Down, true),
       val isLoading: Boolean = true,
       val error: String? = null
)

sealed class SortingDirection {
    object Up : SortingDirection()
    object Down : SortingDirection()
}

sealed class TodoItemOrder(
       val sortingDirection: SortingDirection,
       val showArchived: Boolean
) {

    class Title(
           sortingDirection: SortingDirection,
           showArchived: Boolean
    ) : TodoItemOrder(
           sortingDirection = sortingDirection,
           showArchived = showArchived
    )

    class Time(
           sortingDirection: SortingDirection,
           showArchived: Boolean
    ) : TodoItemOrder(
           sortingDirection = sortingDirection,
           showArchived = showArchived
    )

    class Completed(
           sortingDirection: SortingDirection,
           showArchived: Boolean
    ) : TodoItemOrder(
           sortingDirection = sortingDirection,
           showArchived = showArchived
    )

    fun copy(
           sortingDirection: SortingDirection,
           showArchived: Boolean
    ): TodoItemOrder {
        return when (this) {
            is Completed -> Title(
                   sortingDirection = sortingDirection,
                   showArchived = showArchived
            )

            is Time -> Title(
                   sortingDirection = sortingDirection,
                   showArchived = showArchived
            )

            is Title -> Title(
                   sortingDirection = sortingDirection,
                   showArchived = showArchived
            )
        }
    }
}
