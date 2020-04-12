package com.devmmurray.notes.ui.notes

import com.devmmurray.notes.models.Note
import javax.inject.Inject

class LocalNotesModel @Inject constructor(): INotesModel {

    override fun addNote(note: Note, callback: NotesSuccessCallback) {
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: NotesSuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note, callback: NotesSuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun retrieveNotes(): List<Note> {
        TODO("Not yet implemented")
    }

    override fun getFakeNotesData(): MutableList<Note> = mutableListOf(
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
        Note("Note Twelve")
    )

}