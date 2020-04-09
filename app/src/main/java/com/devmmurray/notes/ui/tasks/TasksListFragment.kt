package com.devmmurray.notes.ui.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmmurray.notes.R

class TasksListFragment : Fragment() {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var touchActionDelegate: TouchActionDelegate
    private lateinit var contentView: TaskListView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
            .apply { contentView = this as TaskListView }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksViewModelBind()
        setContentView()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, tasksViewModel)
    }

    private fun tasksViewModelBind() {
        tasksViewModel =
            ViewModelProvider(this).get(TasksViewModel::class.java)
        tasksViewModel.taskListLiveData.observe(viewLifecycleOwner, Observer {
            contentView.updateList(it)
        })
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}
