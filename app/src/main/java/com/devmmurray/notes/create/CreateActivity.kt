package com.devmmurray.notes.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devmmurray.notes.R
import com.devmmurray.notes.navigation.FRAGMENT_TYPE_KEY
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_NOTE
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_TASK

class CreateActivity :
    AppCompatActivity(),
    CreateNotesFragment.NotesOnFragmentInteractionListener,
    CreateTaskFragment.TasksOnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar?.title = ""

        intent.getStringExtra(FRAGMENT_TYPE_KEY).run {
            if (this == FRAGMENT_VALUE_TASK) {
                createFragment(CreateTaskFragment.newInstance())
            } else if (this == FRAGMENT_VALUE_NOTE) {
                createFragment(CreateNotesFragment.newInstance())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveItem -> {
                supportFragmentManager
                    .findFragmentById(R.id.fragmentHolder)
                    ?.run {
                        if (this is CreateTaskFragment) {
                            this.saveTask { success ->
                                if (success) {
                                    this@CreateActivity.supportFinishAfterTransition()
                                } else {
                                    Toast.makeText(this@CreateActivity,
                                    getString(R.string.could_not_save),
                                    Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        } else if (this is CreateNotesFragment) {
                            this.saveNote { success ->
                                if (success) {
                                    this@CreateActivity.supportFinishAfterTransition()
                                } else {
                                    Toast.makeText(this@CreateActivity,
                                        getString(R.string.could_not_save),
                                    Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun notesOnFragmentInteraction() {
        TODO("Not yet implemented")
    }

    override fun tasksOnFragmentInteractionListener() {
        TODO("Not yet implemented")
    }
}
