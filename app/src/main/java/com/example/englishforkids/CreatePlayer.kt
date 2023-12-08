package com.example.englishforkids

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CreatePlayer: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_player)

        val toolbar: Toolbar = findViewById(R.id.toolbar_common)
        setSupportActionBar(toolbar)
    }
}