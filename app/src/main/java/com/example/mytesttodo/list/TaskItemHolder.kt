package com.example.mytesttodo.list

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import com.example.mytesttodo.databinding.CardWievBinding
import com.example.mytesttodo.sheetFragment.TaskItemClickListener
import java.time.format.DateTimeFormatter

class TaskItemHolder(
    private val context: Context,
    private val binding: CardWievBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItem (taskItem: TaskItem){
        binding.taskText.text = taskItem.textTask

        if (taskItem.isCompleted()){
            binding.taskText.paintFlags = Paint.SUBPIXEL_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.SUBPIXEL_TEXT_FLAG
        }
        binding.checkBox.setImageResource(taskItem.imageResource())
        binding.checkBox.setColorFilter(taskItem.imageColor(context))

        binding.checkBox.setOnClickListener {
         clickListener.completedTaskItem(taskItem)
        }
        binding.CardView.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTime != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.dueTime.text = " "
    }
}