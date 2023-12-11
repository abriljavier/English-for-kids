package com.example.englishforkids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            // MOSTRAR EL MAIN FRAGMENT SI ES LA PRIMERA VEZ QUE SE ABRE LA APP
            val mainFragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment, "mainFragment")
                .commit()
        }
    }

    //LA ASIGNACIÓN E INFLATE DEL MENÚ
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //ABRIR EL DIALOGO DE SABER MÁS
            R.id.menu_about -> {
                var aboutDialog = AboutDialog()
                aboutDialog.show(supportFragmentManager, "AboutDialog")
                return true
            }

            //ABRIR EL DIALOGO DE PUNTUACIONES
            R.id.menu_high_scores -> {
                var database = Database(this)
                try {
                    database.writableDatabase
                    var userList = database.getTopUsers()
                    var scoreDialog = ScoreDialog(userList)
                    scoreDialog.show(supportFragmentManager, "ScoreDialog")
                } finally {
                    database.close()
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


}