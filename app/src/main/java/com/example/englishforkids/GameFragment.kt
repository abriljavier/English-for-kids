package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GameFragment (user: User) : Fragment() {

    companion object {
        const val ARG_SELECTED_USER = "selectedUser"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)



        return view
    }

}
