package com.devmmurray.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val TYPE_ADD_BUTTON = 0
const val TYPE_INFO = 1

abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(data: E, listIndex: Int)
}

abstract class AddButtonViewHolder(view: View) : BaseViewHolder<Unit>(view)

abstract class BaseRecyclerAdapter<T>(
    private val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = masterList.size + 1

    override fun getItemViewType(position: Int): Int = if (position == 0)
        TYPE_ADD_BUTTON else TYPE_INFO

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit, position - 1)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position - 1)
        }
    }

    fun updateList(list: List<T>) {
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
    }
}

