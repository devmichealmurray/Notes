package com.devmmurray.notes.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devmmurray.notes.R
import com.devmmurray.notes.navigation.FRAGMENT_TYPE_KEY
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_NOTE
import com.devmmurray.notes.navigation.FRAGMENT_VALUE_TASK
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(FRAGMENT_TYPE_KEY).run {
            textView2.text = if (this == FRAGMENT_VALUE_TASK) {
                "This is a task"
            } else if (this == FRAGMENT_VALUE_NOTE) {
                "This is a note"
            } else {
                "Something went wrong"
            }
        }
    }
}
