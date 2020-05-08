package com.devmmurray.notes.ui.notes

import com.devmmurray.notes.application.NoteApplication
import com.devmmurray.notes.database.RoomDatabaseClient
import com.devmmurray.notes.models.Note
import javax.inject.Inject

class LocalNotesModel @Inject constructor() : INotesModel {

    private var databaseClient: RoomDatabaseClient =
        RoomDatabaseClient
            .getInstance(
                NoteApplication
                    .instance
                    .applicationContext
            )

    override fun addNote(note: Note, callback: NotesSuccessCallback) {
        databaseClient.notesDAO().addNote(note)
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: NotesSuccessCallback) {
        databaseClient.notesDAO().updateNote(note)
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: NotesSuccessCallback) {
        databaseClient.notesDAO().deleteNote(note)
        callback.invoke(true)
    }

    override fun retrieveNotes(): List<Note> = databaseClient.notesDAO().retrieveNotes()

    override fun getFakeNotesData(): MutableList<Note> = retrieveNotes().toMutableList()

}