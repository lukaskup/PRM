package com.example.myapplication.fragments.list

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(private val mTaskViewModel: TaskViewModel): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent,false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.itemView.findViewById<TextView>(R.id.taskTitle_txt).text = currentTask.title.toString()
        holder.itemView.findViewById<TextView>(R.id.taskPriority_txt).text = "Priority: ${currentTask.priority.toString()}"
        holder.itemView.findViewById<TextView>(R.id.taskProgress_txt).text = "${currentTask.progress.toString()}%"

        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        holder.itemView.findViewById<TextView>(R.id.taskDeadline_txt).text = "Due: ${dateFormat.format(currentTask.deadline)}"
    }

    fun setData(tasks: List<Task>) {
        this.taskList = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}