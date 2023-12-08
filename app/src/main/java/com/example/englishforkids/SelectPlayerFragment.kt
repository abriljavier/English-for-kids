package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.fragment.app.Fragment

class SelectPlayerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragment
        val view = inflater.inflate(R.layout.fragment_select_player, container, false)

        //LLAMO A LA INSTANCIA DE LA BBDD
        val database = Database(requireContext())
        val userList = database.getAllUsers()

        //CONFIGURAR EL BOTÓN DE VOLVER ATRÁS
        var backButton = view.findViewById<ImageButton>(R.id.backButton2)
        backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        //CONFIGURAR EL SPINNER
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userList.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //CONFIGURAR EL BOTÓN DE CONTINUAR PARTIDA
        val continueGameButton = view.findViewById<ImageButton>(R.id.continueGameButton)
        continueGameButton.setOnClickListener {
            val selectedUserName = spinner.selectedItem.toString()
            val selectedUser = userList.find { it.name == selectedUserName }
            if (selectedUser != null) {
                val gameFragment = GameFragment(selectedUser)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, gameFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


        return view
    }
}