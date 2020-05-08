package com.devmmurray.notes.database

import androidx.room.*
import com.devmmurray.notes.models.Note

@Dao
interface NotesDAO {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    fun retrieveNotes(): MutableList<Note>
}