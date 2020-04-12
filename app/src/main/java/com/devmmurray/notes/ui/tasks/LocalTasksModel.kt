package com.devmmurray.notes.ui.tasks

import com.devmmurray.notes.models.Task
import com.devmmurray.notes.models.Todo
import javax.inject.Inject

class LocalTasksModel @Inject constructor(): ITasksModel {

    override fun addTask(task: Task, callback: TaskSuccessCallback) {
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: TaskSuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task, callback: TaskSuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun retrieveTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getFakeTasksData(): MutableList<Task> = mutableListOf(
        Task(
            "Task One", mutableListOf(
                Todo("Todo Number One"),
                Todo("Todo Number Two"),
                Todo("Todo Number Three"),
                Todo("Todo Number Four")
            )
        ),
        Task(
            "Task Two", mutableListOf(
                Todo("Todo Number One"),
                Todo("Todo Number Two"),
                Todo("Todo Number Three"),
                Todo("Todo Number Four")
            )
        ),
        Task(
            "Task Three", mutableListOf(
                Todo("Todo Number One"),
                Todo("Todo Number Two"),
                Todo("Todo Number Three"),
                Todo("Todo Number Four")
            )
        ),
        Task(
            "Task Four", mutableListOf(
                Todo("Todo Number One"),
                Todo("Todo Number Two"),
                Todo("Todo Number Three"),
                Todo("Todo Number Four")
            )
        ),
        Task(
            "Task Five", mutableListOf(
                Todo("Todo Number One"),
                Todo("Todo Number Two"),
                Todo("Todo Number Three"),
                Todo("Todo Number Four")
            )
        )
    )

}