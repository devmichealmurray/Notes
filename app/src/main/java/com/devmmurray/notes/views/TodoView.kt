package com.devmmurray.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.devmmurray.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {


    fun initView(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        descriptionView.text = todo.description
        completedCheckBox.isChecked = todo.isComplete

        if (todo.isComplete) createStrikeThrough()
        setUpCheckStateListener(todo, callback)
    }

    private fun setUpCheckStateListener(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        completedCheckBox.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            todo.isComplete = isChecked

            callback?.invoke(isChecked)
            if (isChecked) createStrikeThrough() else removeStrikeThrough()
        }
    }

    private fun createStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags and
                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}