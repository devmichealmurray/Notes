package com.devmmurray.notes.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.devmmurray.notes.models.Note
import kotlinx.android.synthetic.main.item_notes.view.*

class NoteView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(note: Note, closeButtonClickedCallback: () -> Unit) {
        notesView.text = note.description
        closeButton.setOnClickListener {
            closeButtonClickedCallback.invoke()
        }
    }
}