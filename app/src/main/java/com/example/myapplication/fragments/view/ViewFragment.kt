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
import com.example.myapplication.R
import com.example.myapplication.data.TaskViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.android.synthetic.main.fragment_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class ViewFragment: Fragment() {
    private lateinit var mTaskViewModel: TaskViewModel
    private val args by navArgs<ViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        setHasOptionsMenu(true)

        view.taskViewTitle.text = args.currentTask.title
        view.taskViewPriority.text = "Priority: ${args.currentTask.priority}"
        view.taskViewEstimate.text = "Estimate (minutes): ${args.currentTask.estimateTimeMinutes} m"
        view.taskViewDeadline.text = "Due to: ${args.currentTask.deadline}"
        view.taskViewProgress.text = "${args.currentTask.progress}%"
        view.circularProgressIndicator.progress = args.currentTask.progress

        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.US)
        view.taskViewDeadline.text = dateFormat.format(args.currentTask.deadline)


        view.findViewById<Button>(R.id.shareTaskProgressButton).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.currentTask.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return view
    }
}