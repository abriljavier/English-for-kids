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
                .replace(R.id.fragment_container, mainFragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                // Acción para "Acerca de la app"
                Toast.makeText(this, "Acerca de la app", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_high_scores -> {
                // Acción para "Mejores puntuaciones"
                Toast.makeText(this, "Mejores puntuaciones", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}