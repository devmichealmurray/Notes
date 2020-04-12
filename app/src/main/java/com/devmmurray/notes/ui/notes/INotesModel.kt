package com.devmmurray.notes.ui.notes

import com.devmmurray.notes.models.Note

typealias NotesSuccessCallback = (Boolean) -> Unit

interface INotesModel {

    fun getFakeNotesData(): MutableList<Note>
    fun addNote(note: Note, callback: NotesSuccessCallback)
    fun updateNote(note: Note, callback: NotesSuccessCallback)
    fun deleteNote(note: Note, callback: NotesSuccessCallback)
    fun retrieveNotes(): List<Note>
}