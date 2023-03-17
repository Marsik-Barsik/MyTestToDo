package com.example.mytesttodo.list

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mytesttodo.databinding.CardWievBinding
import com.example.mytesttodo.sheetFragment.TaskItemClickListener


class TaskItemAdapter(
    private val taskItem: List<TaskItem>,
    private val clickListener: TaskItemClickListener
): RecyclerView.Adapter<TaskItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemHolder {
        val form = LayoutInflater.from(parent.context)
        val binding = CardWievBinding.inflate(form,parent,false)
        return TaskItemHolder(parent.context, binding,clickListener)
    }

    override fun getItemCount(): Int = taskItem.size


    override fun onBindViewHolder(holder: TaskItemHolder, position: Int) {
        holder.bindTaskItem(taskItem[position])
    }
}