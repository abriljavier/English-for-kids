package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ContinueGameFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragment
        val view = inflater.inflate(R.layout.fragment_create_player, container, false)

        var volverAtrasButtonTOD = view.findViewById<Button>(R.id.button)
        volverAtrasButtonTOD.setOnClickListener{

        }

        return view
    }
}