package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment

class SelectPlayerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // INFLAR EL DISEÑO DEL FRAGMENT
        val view = inflater.inflate(R.layout.fragment_select_player, container, false)

        //LLAMO A LA INSTANCIA DE LA BBDD Y ME TRAIGO TODOS LOS USUARIOS
        val database = Database(requireContext())
        try {
            val userList = database.getAllUsers()

            //CONFIGURAR EL SPINNER
            val spinner: Spinner = view.findViewById(R.id.spinner)
            val userNames = mutableListOf<String>()
            //LOS NOMBRES COMO VALORES DEL SPINNER
            for (user in userList) {
                userNames.add(user.name)
            }
            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, userNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            //CONFIGURAR EL BOTÓN DE CONTINUAR PARTIDA
            val continueGameButton = view.findViewById<ImageButton>(R.id.continueGameButton)
            continueGameButton.setOnClickListener {
                //SI SE HA SELECCIONADO JUGADOR
                if (spinner.selectedItem != null) {
                    val selectedUserName = spinner.selectedItem.toString()
                    val selectedUser = userList.find { it.name == selectedUserName }
                    if (selectedUser != null) {
                        //LLAMAR AL FRAGMENTO DE JUGAR PASÁNDOLE UN USER
                        val gameFragment = GameFragment(selectedUser)
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, gameFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Seems that there is no player, start creating one",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        } finally {
            database.close()
        }

        //CONFIGURAR EL BOTÓN DE VOLVER ATRÁS
        var backButton = view.findViewById<ImageButton>(R.id.backButton2)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}