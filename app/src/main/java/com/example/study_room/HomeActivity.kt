package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        //button -----------------------------------------------------------------------------------
        val btnStudyRoomBook = findViewById<Button>(R.id.btnStudyRoomBook)
        val btnNews = findViewById<Button>(R.id.btnNews)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnInfo = findViewById<Button>(R.id.btnInfo)

        btnStudyRoomBook.setOnClickListener() {
            selectRoom()
        }

        //news button ------------------------------------------------------------------------------
        btnNews.setOnClickListener() {

        }

        //setting button ---------------------------------------------------------------------------
        btnSettings.setOnClickListener() {

        }

        //info button ------------------------------------------------------------------------------
        btnInfo.setOnClickListener() {

        }
    }

    private fun selectRoom() {
        val intent = Intent(this, SelectRoomActivity::class.java)
        startActivity(intent)
    }
}