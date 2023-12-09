package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScoreDialog(private var scores: List<User>): DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.score_dialog_layout, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ScoreAdapter(scores)

        var goBackButton = view.findViewById<Button>(R.id.goBackButton)
        goBackButton.setOnClickListener{
            this.dismiss()
        }

        return view
    }
}