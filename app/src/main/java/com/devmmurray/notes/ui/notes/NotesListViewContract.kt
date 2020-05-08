package com.devmmurray.notes.ui.notes

import com.devmmurray.notes.models.Note

interface NotesListViewContract {
    fun onDeleteNote(note: Note)
}