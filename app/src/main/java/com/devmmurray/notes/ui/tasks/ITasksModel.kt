package com.devmmurray.notes.ui.tasks

import com.devmmurray.notes.models.Task

typealias TaskSuccessCallback = (Boolean) -> Unit

interface ITasksModel {

    fun getFakeTasksData(): MutableList<Task>

    fun addTask(task: Task, callback: TaskSuccessCallback)
    fun updateTask(task: Task, callback: TaskSuccessCallback)
    fun deleteTask(task: Task, callback: TaskSuccessCallback)
    fun retrieveTasks(): List<Task>
}