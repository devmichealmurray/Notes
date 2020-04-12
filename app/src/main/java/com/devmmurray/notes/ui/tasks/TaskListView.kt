package com.devmmurray.notes.ui.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmmurray.notes.models.Task
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TaskListView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var touchActionDelegate: TasksListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(
        taDelegate: TasksListFragment.TouchActionDelegate,
        daDelegate: TaskListViewContract
    ) {
        setUpDelegate(taDelegate, daDelegate)
        setUpView()
    }

    private fun setUpDelegate(
        taDelegate: TasksListFragment.TouchActionDelegate,
        daDelegate: TaskListViewContract
    ) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        tasksAdapter = TasksAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )
        tasks_recycler_view.adapter = tasksAdapter
    }

    fun updateList(list: List<Task>) {
        tasksAdapter.updateList(list)
    }
}