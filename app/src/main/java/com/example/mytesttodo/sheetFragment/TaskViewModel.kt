package com.example.mytesttodo.sheetFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytesttodo.list.TaskItem
import java.time.LocalDate
import java.time.LocalTime

import java.util.UUID

class TaskViewModel: ViewModel() {
    var taskItem = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItem.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem) {
        val list = taskItem.value
        list!!.add(newTask)
        taskItem.postValue(list)
    }

    fun updateTaskItem(id: UUID, textTask: String, dueTime: LocalTime?) {
        val list = taskItem.value
        val task = list!!.find { it.id == id }!!
        task.textTask = textTask
        task.dueTime = dueTime
        taskItem.postValue(list)
    }

    fun setCompleted(TaskItem: TaskItem) {
        val list = taskItem.value
        val task = list!!.find { it.id == TaskItem.id }!!
        if (task.completedDate == null)
         task.completedDate = LocalDate.now()
        taskItem.postValue(list)
    }
}

