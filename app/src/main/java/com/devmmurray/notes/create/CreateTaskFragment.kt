package com.devmmurray.notes.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devmmurray.notes.R
import com.devmmurray.notes.foundations.ApplicationScope
import com.devmmurray.notes.foundations.NullFieldChecker
import com.devmmurray.notes.foundations.StateChangeTextWatcher
import com.devmmurray.notes.models.Task
import com.devmmurray.notes.models.Todo
import com.devmmurray.notes.ui.tasks.ITasksModel
import com.devmmurray.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import toothpick.Toothpick
import javax.inject.Inject

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {

    @Inject
    lateinit var model: ITasksModel
    private var listener: TasksOnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTaskView.taskEditText.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                }
                super.afterTextChanged(s)
            }
        })
    }

    private fun addTodoView() {
        if (canAddTodos()) {
            val view = (LayoutInflater
                .from(context)
                .inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView)
                .apply {
                    todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                        override fun afterTextChanged(s: Editable?) {
                            if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                                addTodoView()
                            } else if (s.isNullOrEmpty() && !previousValue.isNullOrEmpty()) {
                                removeTodoView(this@apply)
                                if (containerView.childCount == MAX_TODO_COUNT) {
                                    addTodoView()
                                }
                            }
                            super.afterTextChanged(s)
                        }
                    })
                }
            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }

    private fun canAddTodos(): Boolean =
        containerView.childCount < MAX_TODO_COUNT + 1 &&
                !(containerView.getChildAt(containerView.childCount - 1) as NullFieldChecker).hasNullField()

    private fun isTaskEmpty(): Boolean = createTaskView.taskEditText.editableText.isNullOrEmpty()

    fun saveTask(callback: (Boolean) -> Unit) {
        createTask()?.let {
            model.addTask(it) {
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    fun createTask(): Task? {
        if (!isTaskEmpty()) {
            containerView.run {
                var taskField: String? = null
                val todoList: MutableList<Todo> = mutableListOf()
                for (i in 0 until containerView.childCount) {
                    if (i == 0) {
                        taskField = containerView
                            .getChildAt(i)
                            .taskEditText
                            .editableText
                            .toString()
                    } else {
                        if (!containerView
                                .getChildAt(i)
                                .todoEditText
                                .editableText
                                .isNullOrEmpty()) {
                            todoList.add(
                                Todo(containerView
                                    .getChildAt(i)
                                    .todoEditText
                                    .editableText
                                    .toString())
                            )
                        }
                    }
                }
                return taskField?.let { Task(it, todoList) }
            }
        } else {
            return null
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TasksOnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface TasksOnFragmentInteractionListener {
        fun tasksOnFragmentInteractionListener()
    }

    companion object {
        fun newInstance() =
            CreateTaskFragment()
    }
}