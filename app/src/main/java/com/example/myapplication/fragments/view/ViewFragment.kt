package com.example.myapplication.fragments.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.myapplication.data.TaskViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.text.SimpleDateFormat
import java.util.*

class ViewFragment: Fragment() {
    private lateinit var mTaskViewModel: TaskViewModel
    private val args by navArgs<ViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        setHasOptionsMenu(true)

        view.findViewById<TextView>(R.id.tv_taskDetailsTitle).text = args.currentTask.name
        view.findViewById<TextView>(R.id.tv_taskDetailsPrio).text = args.currentTask.priority.toString()
        view.findViewById<TextView>(R.id.tv_taskDetailsEstimatedH).text = args.currentTask.estimatedTimeInHours.toString()
        view.findViewById<TextView>(R.id.tv_taskDetailsDeadline).text = args.currentTask.deadline.toString()
        view.findViewById<CircularProgressIndicator>(R.id.circularProgressIndicatorPercentage).progress = args.currentTask.percentageDone
        view.findViewById<TextView>(R.id.percentageTextIndicator).text = "${args.currentTask.percentageDone.toString()}%"

        val myFormat = "dd/MM/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        view.findViewById<TextView>(R.id.tv_taskDetailsDeadline).text = dateFormat.format(args.currentTask.deadline)


        view.findViewById<Button>(R.id.shareTaskButton).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I am ${args.currentTask.percentageDone}% done with this task!")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return view
    }
}