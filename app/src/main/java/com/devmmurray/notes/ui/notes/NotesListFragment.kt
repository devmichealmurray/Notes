package com.devmmurray.notes.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmmurray.notes.R
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesListFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    lateinit var touchActionDelegate: TouchActionDelegate
    private lateinit var notesAdapter: NotesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is NotesListFragment.TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notes_recycler_view.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter(touchActionDelegate = touchActionDelegate)
        notes_recycler_view.adapter = notesAdapter
        notesViewModelBind()
    }

    private fun notesViewModelBind() {
        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.noteListLiveData.observe(viewLifecycleOwner, Observer {
            notesAdapter.updateList(it)
        })
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }
}
