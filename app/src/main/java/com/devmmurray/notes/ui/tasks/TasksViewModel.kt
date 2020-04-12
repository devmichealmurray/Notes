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

    private val _taskListData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        _taskListData.postValue(localTasksModel.getFakeTasksData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isCompleted: Boolean) {
        _taskListData.value
            ?.get(taskIndex)
            ?.todos
            ?.get(todoIndex)
            ?.isComplete = isCompleted
    }
}
