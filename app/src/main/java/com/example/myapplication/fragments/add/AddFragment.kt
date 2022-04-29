package com.example.myapplication.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_add.spinner

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val priorities = resources.getStringArray(R.array.task_priorities)

        if (spinner != null) {
            val adapter = ArrayAdapter(container.context,
                android.R.layout.simple_spinner_item, priorities)
            spinner.adapter = adapter

        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}