package com.devmmurray.notes.ui.tasks

import com.devmmurray.notes.application.NoteApplication
import com.devmmurray.notes.database.RoomDatabaseClient
import com.devmmurray.notes.models.Task
import javax.inject.Inject

class LocalTasksModel @Inject constructor() : ITasksModel {

    private var databaseClient: RoomDatabaseClient =
        RoomDatabaseClient
            .getInstance(
                NoteApplication
                    .instance
                    .applicationContext
            )

    override fun addTask(task: Task, callback: TaskSuccessCallback) {
        databaseClient.tasksDAO().addTask(task)
        addTodosInTask(task)
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: TaskSuccessCallback) {
        databaseClient.tasksDAO().updateTask(task)
        callback.invoke(true)
    }

    override fun deleteTask(task: Task, callback: TaskSuccessCallback) {
        databaseClient.tasksDAO().deleteTask(task)
        callback.invoke(true)
    }

    private fun addTodosInTask(task: Task) {
        task.todos.forEach { todo ->
            databaseClient.tasksDAO().addTodo(todo)
        }
    }

    override fun retrieveTasks(): List<Task> = databaseClient.tasksDAO().retrieveTasks()

    override fun getFakeTasksData(): MutableList<Task> = retrieveTasks().toMutableList()

}