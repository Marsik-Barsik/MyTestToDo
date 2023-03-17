package com.example.mytesttodo.sheetFragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mytesttodo.databinding.FragmentNewTaskBinding
import com.example.mytesttodo.list.TaskItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class FragmentNewTask (var taskItem: TaskItem?): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity =requireActivity()

        if (taskItem !=null) {
            binding.textView.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.textInput.text = editable.newEditable(taskItem!!.textTask)
            if (taskItem!!.dueTime!=null){
                dueTime = taskItem!!.dueTime!!
                updateTimeButtonText()
            }

            if (taskItem!!.dueTime != null){
                dueTime = taskItem!!.dueTime!!
                updateTimeButtonText()
            }
        }
            else{
            binding.textView.text = "New Task"
        }

        taskViewModel = ViewModelProvider(activity)[TaskViewModel::class.java] //---> get.(TaskViewModel::class.java)
        binding.buttonSave.setOnClickListener{
            saveAction()
        }
        binding.buttonEnterTime.setOnClickListener {
            openTimePicker()
        }

    }

    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{
            _, selectHour,selectMinuted -> dueTime = LocalTime.of(selectHour,selectMinuted)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog (activity, listener,dueTime!!.hour,dueTime!!.minute,true)
        dialog.setTitle("Task")
        dialog.show()
    }

    private fun updateTimeButtonText() {
        binding.buttonEnterTime.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    private fun saveAction() {
       val textInput = binding.textInput.text.toString()
      if(taskItem == null){
          val newTask = TaskItem(textInput,null,null)
          taskViewModel.addTaskItem(newTask)
      }
        else{
            taskViewModel.updateTaskItem(taskItem!!.id,textInput, null)
      }
        binding.textInput.setText(" ")
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentNewTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

}