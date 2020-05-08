package com.devmmurray.notes.database

import androidx.room.*
import com.devmmurray.notes.models.Task
import com.devmmurray.notes.models.TaskEntity
import com.devmmurray.notes.models.Todo

@Dao
interface TasksDAO {
    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): MutableList<Task>
}