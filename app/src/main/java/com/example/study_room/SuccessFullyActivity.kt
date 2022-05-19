package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SuccessFullyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successfully)

        supportActionBar?.hide()

        var handle = Handler().postDelayed({
            val intent = Intent(this, SelectRoomActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}