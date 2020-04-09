package com.devmmurray.notes.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmmurray.notes.models.Task
import com.devmmurray.notes.models.Todo

class TasksViewModel : ViewModel(), TaskListViewContract {

    private val _taskListData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListData

    init {
        _taskListData.postValue(getFakeData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isCompleted: Boolean) {
        _taskListData.value
            ?.get(taskIndex)
            ?.todos
            ?.get(todoIndex)
            ?.isComplete = isCompleted

    }

    private fun getFakeData(): MutableList<Task> = mutableListOf(
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

    private val todoList = mutableListOf(
        Todo("Todo Number One"),
        Todo("Todo Number Two"),
        Todo("Todo Number Three"),
        Todo("Todo Number Four")
    )
}
