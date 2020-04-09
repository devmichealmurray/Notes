package com.devmmurray.notes.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devmmurray.notes.R
import com.devmmurray.notes.foundations.BaseRecyclerAdapter
import com.devmmurray.notes.foundations.BaseViewHolder
import com.devmmurray.notes.foundations.TYPE_INFO
import com.devmmurray.notes.models.Task
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_TASK
import com.devmmurray.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class TasksAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TasksListFragment.TouchActionDelegate,
    val dataActionDelegate: TaskListViewContract
) :
    BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            TasksViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_tasks, parent, false)
            )
        } else {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_add_button, parent, false)
            )
        }

    inner class AddButtonViewHolder(view: View) :
        com.devmmurray.notes.foundations.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(FRAGMENT_VALUE_TASK)
            }
        }
    }

    inner class TasksViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(data: Task, listIndex: Int) {
            (view as TaskView).initView(data) { todoIndex, isChecked ->
                dataActionDelegate.onTodoUpdated(listIndex, todoIndex, isChecked)
            }
        }
    }


}
