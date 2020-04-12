package com.devmmurray.notes.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmmurray.notes.R

class NotesListFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var touchActionDelegate: TouchActionDelegate
    private lateinit var contentView: NotesListView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is TouchActionDelegate) touchActionDelegate = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(R.layout.fragment_notes, container, false)
            .apply {
                contentView = this as NotesListView
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModelBind()
        setContentView()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, notesViewModel)
    }

    private fun notesViewModelBind() {
        notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.noteListLiveData.observe(viewLifecycleOwner, Observer {
            contentView.updateList(it)
        })
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

//    companion object {
//        fun newInstance() = NotesListFragment()
//    }
}
