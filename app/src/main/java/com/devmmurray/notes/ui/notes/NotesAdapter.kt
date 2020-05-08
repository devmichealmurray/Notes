package com.devmmurray.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devmmurray.notes.R
import com.devmmurray.notes.foundations.BaseRecyclerAdapter
import com.devmmurray.notes.foundations.BaseViewHolder
import com.devmmurray.notes.foundations.TYPE_INFO
import com.devmmurray.notes.models.Note
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_NOTE
import com.devmmurray.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*


class NotesAdapter(
    notesList: MutableList<Note> = mutableListOf(),
    val touchActionDelegate: NotesListFragment.TouchActionDelegate,
    private val dataActionDelegate: NotesListViewContract
) : BaseRecyclerAdapter<Note>(notesList) {

    inner class AddButtonViewHolder(view: View) :
        com.devmmurray.notes.foundations.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)
            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(FRAGMENT_VALUE_NOTE)
            }
        }
    }

    inner class NotesViewHolder(
        view: View
    ) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note, listIndex: Int) {
            (view as NoteView).initView(data) {
                dataActionDelegate.onDeleteNote(masterList[listIndex])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            NotesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_notes, parent, false)
            )
        } else {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_add_button, parent, false)
            )
        }

}