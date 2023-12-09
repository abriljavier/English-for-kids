package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class GameFragment(private val user: User) : Fragment() {

    private lateinit var database: Database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //LAS INSTANCIAS DE LOS BOTONES
        var saveExitBtn = view.findViewById<Button>(R.id.saveBnt)
        var nextBtn = view.findViewById<Button>(R.id.nextBtn)

        //LAS INSTANCIAS DE LOS OBJETOS DE LA VIEW
        var title = view.findViewById<TextView>(R.id.titleOfCategory)
        var image = view.findViewById<TextView>(R.id.image)
        var inputUsr = view.findViewById<EditText>(R.id.inputUsr)

        //LLAMO A LA INSTANCIA DE LA BBDD
        database = Database(requireContext())
        var currentLevel: Level? = database.getLevelById(user.idLevel.toLong())
        if (currentLevel != null) {
            val levelId = currentLevel.id
            val levelName = currentLevel.name
            val levelCategory = currentLevel.category
            val levelImage = currentLevel.image
            //ASIGNAR LOS VALORES DE LOS OBJETOS DEL TEXTO AL NIVEL QUE ME TRAIGO DE LA BBDD
            title.text = levelCategory
            image.text = levelImage

            nextBtn.setOnClickListener{
                if (inputUsr.text.toString() !=null && inputUsr.text.toString() !=""){
                    if (inputUsr.text.toString() == levelName){
                        database.updateUserLevel(user.id, levelId!!+1)
                        openNextLevel()
                    }
                } else {
                    Toast.makeText(context, "You have to enter the word!", Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            println("El nivel no existe.")
        }

        saveExitBtn.setOnClickListener {
            parentFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view
    }

    private fun openNextLevel() {
        val updatedUser = User(user.id, user.name, user.score, user.idLevel + 1)
        val nextGameFragment = GameFragment(updatedUser)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextGameFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

}
