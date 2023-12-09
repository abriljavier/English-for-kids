package com.example.englishforkids

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.englishforkids.R

class CreatePlayerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // INFLAR EL DISEÑO
        val view = inflater.inflate(R.layout.fragment_create_player, container, false)

        // BOTÓN DE VOLVER ATRÁS
        val backButton = view.findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        //BOTÓN DE CREAR PLAYER Y QUERY PARA INSERTAR
        val createButton = view.findViewById<ImageButton>(R.id.createPlayer)
        createButton.setOnClickListener {
            var nameOfTheNewPlayer =
                view.findViewById<EditText>(R.id.PlayerNameInput).text.toString()
            val database = Database(requireContext())
            val userList = database.getAllUserNames()
            if (nameOfTheNewPlayer != "") {
                if (nameOfTheNewPlayer.length<10) {
                    if (!userList.contains(nameOfTheNewPlayer)) {
                        val database = Database(requireContext())
                        val userId = database.addUser(nameOfTheNewPlayer, 0, 1)
                        if (userId != -1L) {
                            Toast.makeText(
                                context,
                                "User created correctly! Welcome $nameOfTheNewPlayer",
                                Toast.LENGTH_SHORT
                            ).show()
                            database.close()
                            parentFragmentManager.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "There is an error in the database, please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        database.close()
                    } else {
                        Toast.makeText(
                            context,
                            "This username is not available! Try another",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }else{
                    Toast.makeText(context, "The name is too long!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }
}