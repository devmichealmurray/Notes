package com.devmmurray.notes.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.devmmurray.notes.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_task.view.*

class CreateTaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyAttr: Int = 1
): LinearLayout(context, attrs, defStyAttr), NullFieldChecker {

    override fun hasNullField(): Boolean = taskEditText.editableText.isNullOrEmpty()
}