package com.example.mytesttodo.sheetFragment

import com.example.mytesttodo.list.TaskItem

interface TaskItemClickListener {
    fun editTaskItem (taskItem: TaskItem)
    fun completedTaskItem (taskItem: TaskItem)
}