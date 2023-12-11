package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // INFLAR EL DISEÑO
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // BOTÓN PARA CREAR NUEVO JUGADOR
        val createPlayerButton = view.findViewById<ImageButton>(R.id.createPlayerButton)
        createPlayerButton.setOnClickListener {
            // ABRIR EL FRAGMENT DE CREAR NUEVO JUGADOR
            val createPlayerFragment = CreatePlayerFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, createPlayerFragment)
                .addToBackStack(null)
                .commit()
        }
        //BOTÓN PARA SELECCIONAR JUGADOR
        val selectPlayerButton = view.findViewById<ImageButton>(R.id.selectPlayerButton)
        selectPlayerButton.setOnClickListener{
            //FRAGMENT DE SELECCIONAR JUGADOR
            val selectPlayerFragment = SelectPlayerFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectPlayerFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}