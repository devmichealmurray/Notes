package com.devmmurray.notes.ui.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmmurray.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesListView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var notesAdapter: NotesAdapter
    private lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: NotesListViewContract


    fun initView(
        taDelegates: NotesListFragment.TouchActionDelegate,
        daDelegate: NotesListViewContract
    ) {
        setUpDelegates(taDelegates, daDelegate)
        setUpView()
    }

    private fun setUpDelegates(
        taDelegates: NotesListFragment.TouchActionDelegate,
        daDelegate: NotesListViewContract
    ) {
        touchActionDelegate = taDelegates
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        notes_recycler_view.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter(touchActionDelegate = touchActionDelegate)
        notes_recycler_view.adapter = notesAdapter
    }

    fun updateList(list: List<Note>) {
        notesAdapter.updateList(list)
    }

}