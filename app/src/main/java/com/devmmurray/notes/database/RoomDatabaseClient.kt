package com.devmmurray.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devmmurray.notes.models.Note
import com.devmmurray.notes.models.Tag
import com.devmmurray.notes.models.TaskEntity
import com.devmmurray.notes.models.Todo

private const val DATABASE_SCHEMA_VERSION = 1
private const val DB_NAME = " local_db_name"

@Database(
    version = DATABASE_SCHEMA_VERSION, entities = [
        TaskEntity::class,
        Todo::class,
        Tag::class,
        Note::class]
)
abstract class RoomDatabaseClient : RoomDatabase() {

    abstract fun notesDAO(): NotesDAO
    abstract fun tasksDAO(): TasksDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context): RoomDatabaseClient {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(
                context,
                RoomDatabaseClient::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}