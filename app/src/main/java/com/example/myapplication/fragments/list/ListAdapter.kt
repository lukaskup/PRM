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
import com.example.myapplication.fragments.view.ViewFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask = taskList[position]

        holder.itemView.findViewById<TextView>(R.id.taskTitle_txt).text = currentTask.title
        holder.itemView.findViewById<TextView>(R.id.taskPriority_txt).text = "Priority: ${currentTask.priority}"
        holder.itemView.findViewById<TextView>(R.id.taskProgress_txt).text = "${currentTask.progress}%"
        holder.itemView.findViewById<ConstraintLayout>(R.id.tasksList).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToViewFragment(currentTask)
            holder.itemView.findNavController().navigate(action)
        }

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