package com.devmmurray.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmmurray.notes.models.Note

class NotesViewModel : ViewModel() {
    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        _noteListLiveData.postValue(getFakeData())
    }

fun getFakeData(): MutableList<Note> = mutableListOf(
    Note("Note One"),
    Note("Note Two"),
    Note("Note Three"),
    Note("Note Four"),
    Note("Note Five"),
    Note("Note Six"),
    Note("Note Seven"),
    Note("Note Eight"),
    Note("Note Nine"),
    Note("Note Ten"),
    Note("Note Eleven"),
    Note("Note Twelve"))
}