package com.example.myapplication.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    private val myCalendar: Calendar = Calendar.getInstance()
    private var deadline: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.saveTask.setOnClickListener{
            saveData(view)
        }

        return view
    }

    private fun saveData(view: View){
        val name = view.taskName.text.toString()
        val priority = view.taskPriority.text.toString()
        val estimate = view.taskEstimate.text.toString()

        if(inputCheck(name) && inputCheck(priority) && inputCheck(estimate)) {
            var task = Task(0, name, priority.toInt(), 0, deadline!!, estimate.toInt())
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Task saved", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(value: String): Boolean{
        return !(TextUtils.isEmpty(value))
    }

    private fun updateLabel(editText: EditText) {
        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        editText.setText(dateFormat.format(myCalendar.time))
        deadline = myCalendar.time
    }
}