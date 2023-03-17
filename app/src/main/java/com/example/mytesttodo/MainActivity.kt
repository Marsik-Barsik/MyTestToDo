package com.example.mytesttodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytesttodo.databinding.ActivityMainBinding
import com.example.mytesttodo.list.TaskItem
import com.example.mytesttodo.list.TaskItemAdapter
import com.example.mytesttodo.sheetFragment.FragmentNewTask
import com.example.mytesttodo.sheetFragment.TaskItemClickListener
import com.example.mytesttodo.sheetFragment.TaskViewModel

class MainActivity : AppCompatActivity(), TaskItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var taskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        taskViewModel = ViewModelProvider (this)[TaskViewModel::class.java]
        binding.buttonAdd.setOnClickListener{
            FragmentNewTask(null).show(supportFragmentManager,"NewTaskTag ")
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskViewModel.taskItem.observe(this){
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        FragmentNewTask(taskItem).show(supportFragmentManager,"NewTaskTag")
    }

    override fun completedTaskItem(taskItem: TaskItem) {
       taskViewModel.setCompleted(taskItem)
    }
}