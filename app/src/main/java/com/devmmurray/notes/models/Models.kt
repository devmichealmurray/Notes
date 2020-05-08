package com.devmmurray.notes.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*


class Task(
    title: String,
    @Relation(
        parentColumn = "uid",
        entityColumn = "taskID",
        entity = Todo::class
    )
    val todos: MutableList<Todo> = mutableListOf(),
    tag: Tag? = null
) : TaskEntity(title = title, tag = tag) {
    init {
        todos.forEach {
            it.taskID = uid
        }
    }
}
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity(tableName = "tasks")
open class TaskEntity(
    @PrimaryKey
    var uid: Long = UUID.randomUUID().leastSignificantBits,
    @ColumnInfo
    var title: String,
    @Embedded
    var tag: Tag? = null
)

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @ForeignKey(
        parentColumns = ["uid"],
        childColumns = ["taskID"],
        entity = TaskEntity::class,
        onDelete = CASCADE
    )
    @ColumnInfo
    var taskID: Long? = null,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var isComplete: Boolean = false
)
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @ColumnInfo
    var description: String,
    @Embedded
    var tag: Tag? = null
)

@Entity(tableName = "tag")
data class Tag(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "color_res_id")
    val colorResId: Int
)

