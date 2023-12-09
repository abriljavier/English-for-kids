package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class AboutDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_layout, container, false)

        var goBackButton = view.findViewById<Button>(R.id.goBack)
        goBackButton.setOnClickListener{
            this.dismiss()
        }

        return view
    }
}