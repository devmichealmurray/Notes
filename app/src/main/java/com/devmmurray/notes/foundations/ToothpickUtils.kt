package com.devmmurray.notes.foundations

import com.devmmurray.notes.ui.notes.INotesModel
import com.devmmurray.notes.ui.notes.LocalNotesModel
import com.devmmurray.notes.ui.tasks.ITasksModel
import com.devmmurray.notes.ui.tasks.LocalTasksModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply { 
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module() {
    init {
        bind(INotesModel::class.java).toInstance(LocalNotesModel())
        bind(ITasksModel::class.java).toInstance(LocalTasksModel())
    }
}