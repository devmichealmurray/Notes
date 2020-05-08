package com.devmmurray.notes.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmmurray.notes.foundations.ApplicationScope
import com.devmmurray.notes.models.Task
import toothpick.Toothpick
import javax.inject.Inject

class TasksViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var localTasksModel: ITasksModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        _taskListLiveData.postValue(localTasksModel.retrieveTasks().toMutableList())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isCompleted: Boolean) {
        _taskListLiveData.value
            ?.get(taskIndex)
            ?.todos
            ?.get(todoIndex)
            ?.isComplete = isCompleted
    }
}
