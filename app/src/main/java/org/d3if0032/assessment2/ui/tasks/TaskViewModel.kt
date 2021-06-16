package org.d3if0032.assessment2.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import org.d3if0032.assessment2.data.TaskDao


class TaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
    ) : ViewModel() {
}