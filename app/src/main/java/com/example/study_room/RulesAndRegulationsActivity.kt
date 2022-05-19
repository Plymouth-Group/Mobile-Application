package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RulesAndRegulationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rulesandregulation)

        supportActionBar?.hide()

        //button -----------------------------------------------------------------------------------
        val btnAgree = findViewById<Button>(R.id.btnAgree)
        val btnCancel = findViewById<Button>(R.id.btnCancel)


        btnAgree.setOnClickListener() {
            val intent = Intent(this, BookNowActivity::class.java)
            startActivity(intent)
        }

        btnCancel.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
