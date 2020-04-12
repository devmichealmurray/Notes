package com.devmmurray.notes.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devmmurray.notes.R
import com.devmmurray.notes.foundations.ApplicationScope
import com.devmmurray.notes.foundations.NullFieldChecker
import com.devmmurray.notes.models.Note
import com.devmmurray.notes.ui.notes.INotesModel
import kotlinx.android.synthetic.main.fragment_create_notes.*
import toothpick.Toothpick
import javax.inject.Inject

class CreateNotesFragment : Fragment(), NullFieldChecker {

    @Inject
    lateinit var model: INotesModel
    private var listener: NotesOnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_notes, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NotesOnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun saveNote(callback: (Boolean) -> Unit) {
        createNote()?.let {
            model.addNote(it) {
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    private fun createNote(): Note? = if (!hasNullField())
        Note(notesEditText.editableText.toString())
    else null

    interface NotesOnFragmentInteractionListener {
        fun notesOnFragmentInteraction()
    }

    override fun hasNullField(): Boolean = notesEditText.editableText.isNullOrEmpty()

    companion object {
        fun newInstance() = CreateNotesFragment()
    }
}
