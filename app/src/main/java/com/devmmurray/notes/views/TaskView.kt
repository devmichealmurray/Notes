package com.devmmurray.notes.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.devmmurray.notes.R
import com.devmmurray.notes.models.Task
import kotlinx.android.synthetic.main.item_tasks.view.*

class TaskView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var task: Task

    fun initView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit) {
        this.task = task
        titleView.text = task.title
        task.todos.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater
                .from(context)
                .inflate(R.layout.view_todo, todo_container, false) as TodoView)
                .apply {
                    initView(todo) { isChecked ->
                        todoCheckedCallback.invoke(todoIndex, isChecked)
                        if (isTaskComplete()) this@TaskView.titleView.setStrikeThrough()
                        else this@TaskView.titleView.removeStrikeThrough()
                    }
                }
            todo_container.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }

}