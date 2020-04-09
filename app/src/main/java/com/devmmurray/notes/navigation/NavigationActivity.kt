package com.devmmurray.notes.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devmmurray.notes.R
import com.devmmurray.notes.create.CreateActivity
import com.devmmurray.notes.ui.notes.NotesListFragment
import com.devmmurray.notes.ui.tasks.TasksListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

const val FRAGMENT_TYPE_KEY = "f_t_k"
const val FRAGMENT_VALUE_NOTE = "f_v_n"
const val FRAGMENT_VALUE_TASK = "f_v_t"

class NavigationActivity : AppCompatActivity(),
    TasksListFragment.TouchActionDelegate,
    NotesListFragment.TouchActionDelegate {

    private val mOnNavigationItemSelectedListener = BottomNavigationView
        .OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tasks -> {
                    val newInstance = TasksListFragment.newInstance().apply {
                        replaceFragment(this)
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notes -> {
                    val newInstance = NotesListFragment.newInstance().apply {
                        replaceFragment(this)
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.fragment_holder)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tasks,
                R.id.navigation_notes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun goToCreateActivity(fragmentValue: String) {
        startActivity(Intent(this, CreateActivity::class.java).apply {
            putExtra(FRAGMENT_TYPE_KEY, fragmentValue)
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onAddButtonClicked(value: String) {
        goToCreateActivity(value)
    }

}
