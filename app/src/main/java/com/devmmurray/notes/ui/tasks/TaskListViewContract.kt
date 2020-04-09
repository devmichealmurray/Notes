package com.devmmurray.notes.ui.tasks

interface TaskListViewContract {

    fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isCompleted: Boolean)
}