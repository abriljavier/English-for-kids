package com.example.englishforkids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.Locale

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
        var image = view.findViewById<ImageView>(R.id.image)
        var inputUsr = view.findViewById<EditText>(R.id.inputUsr)
        var playerScore = view.findViewById<TextView>(R.id.playerScore)

        //LLAMO A LA INSTANCIA DE LA BBDD
        database = Database(requireContext())
        try {
            var currentLevel: Level? = database.getLevelById(user.idLevel.toLong())
            if (currentLevel != null) {
                val levelId = currentLevel.id
                val levelName = currentLevel.name
                val levelCategory = currentLevel.category
                //AVISAR CUANDO CAMBIA DE CATEGORIA
                if (levelId==6 || levelId==12){
                    Toast.makeText(context, "Congrats! you advance one category!", Toast.LENGTH_SHORT).show()
                } else if (levelId == 1){
                    Toast.makeText(context, "Time to start the game!", Toast.LENGTH_SHORT).show()
                }
                //ASIGNAR LOS VALORES DE LOS OBJETOS DEL TEXTO AL NIVEL QUE ME TRAIGO DE LA BBDD
                when(levelCategory){
                    "SHAPES"->title.text = "1/3 $levelCategory"
                    "VEHICLES"->title.text = "2/3 $levelCategory"
                    "ANIMALS"->title.text = "3/3 $levelCategory"
                }
                playerScore.text = "${user.name} you have a score of ${user.score} points "
                val imageResourceId = resources.getIdentifier(levelName, "drawable", requireContext().packageName)
                image.setImageResource(imageResourceId)
                nextBtn.setOnClickListener{
                    if (inputUsr.text.toString() !=null && inputUsr.text.toString() !=""){
                        if (inputUsr.text.toString().toLowerCase() == levelName){
                            database.updateUserLevel(user.id, levelId!!+1, user.score+1)
                            Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                            if (user.idLevel==16){
                                showGameWonDialog()
                            }
                            openNextLevel()
                        } else {
                            Toast.makeText(context, "Sorry, it's not correct, try again :D", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "You have to enter the word!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        } finally {
            database.close()
        }

        saveExitBtn.setOnClickListener {
            parentFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view
    }

    private fun openNextLevel() {
        val newLevel = if (user.idLevel < 16) user.idLevel + 1 else 1
        val updatedUser = User(user.id, user.name, user.score + 1, newLevel)
        val nextGameFragment = GameFragment(updatedUser)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextGameFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showGameWonDialog() {
        val dialogFragment = GameWonDialogFragment()
        dialogFragment.show(parentFragmentManager, "GameWonDialogFragment")
    }

}
