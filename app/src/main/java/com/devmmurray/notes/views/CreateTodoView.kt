package com.devmmurray.notes.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.devmmurray.notes.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_todo.view.*

class CreateTodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyAttr: Int = 1
): ConstraintLayout(context, attrs, defStyAttr), NullFieldChecker {

    override fun hasNullField(): Boolean = todoEditText.editableText.isNullOrEmpty()
}