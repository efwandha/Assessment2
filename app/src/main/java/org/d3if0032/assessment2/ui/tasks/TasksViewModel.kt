package org.d3if0032.assessment2.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import org.d3if0032.assessment2.data.TaskDao


class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
    ) : ViewModel() {

        val  searchQuery = MutableStateFlow("")

        private val tasksFlow = searchQuery.flatMapLatest {
            taskDao.getTasks(it)
        }

        val tasks = tasksFlow.asLiveData()
}