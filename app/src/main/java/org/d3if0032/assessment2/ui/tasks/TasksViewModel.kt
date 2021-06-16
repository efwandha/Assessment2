package org.d3if0032.assessment2.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import org.d3if0032.assessment2.data.TaskDao


class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
    ) : ViewModel() {

        val searchQuery = MutableStateFlow("")

        val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
        val hideCompleted = MutableStateFlow(false)

        private val tasksFlow = combine(
            searchQuery,
            sortOrder,
            hideCompleted
        ){query, sortOrder, hideCompleted ->
            Triple(query, sortOrder, hideCompleted)
        }
            .flatMapLatest { (query, sortOrder, hidecompleted) ->
            taskDao.getTasks(query, sortOrder, hidecompleted)
        }

        val tasks = tasksFlow.asLiveData()
}
enum class SortOrder{ BY_NAME, BY_DATE}