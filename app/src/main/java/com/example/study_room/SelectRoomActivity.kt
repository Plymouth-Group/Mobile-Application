package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class SelectRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectroom)

        supportActionBar?.hide()

        //button -----------------------------------------------------------------------------------
        val btnBook = findViewById<Button>(R.id.btnBook)
        val btnHomeBack = findViewById<Button>(R.id.btnHomeBack)
        val btnBookSearch = findViewById<Button>(R.id.btnBookSearch)

        getBookingSearch()

        btnBookSearch.setOnClickListener() {
            getBookingSearch()
        }

        btnHomeBack.setOnClickListener() {
            goingHome()
        }

        btnBook.setOnClickListener() {
            goingBook()
        }
    }

    private fun goingBook() {
        val intent = Intent(this, BookNowActivity::class.java)
        startActivity(intent)
    }

    private fun goingHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun getBookingSearch() {
        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("room")
            .get()
            .addOnCompleteListener {
                val room = StringBuffer()

                if (it.isSuccessful) {
                    for (document in it.result) {
                        room.append(document.data.getValue("roomNo"))
                            .append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t")
                            .append(document.data.getValue("available"))
                            .append("\t\t\t\t\t\t\t\t\t\t\t\t\t")
                            .append(document.data.getValue("floor")).append("\n")
                    }
                }
                val data = findViewById<TextView>(R.id.txtData)
                data.setText(room)
            }
    }
}