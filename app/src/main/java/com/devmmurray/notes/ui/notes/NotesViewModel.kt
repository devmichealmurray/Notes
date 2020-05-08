package com.devmmurray.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmmurray.notes.foundations.ApplicationScope
import com.devmmurray.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NotesViewModel : ViewModel(), NotesListViewContract {

    @Inject
    lateinit var localNotesModel: INotesModel

    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        _noteListLiveData.postValue(localNotesModel.retrieveNotes() as MutableList<Note>?)
    }

    override fun onDeleteNote(note: Note) {
        localNotesModel.deleteNote(note) {
            if (it) {
                loadData()
            }
        }
    }

}
