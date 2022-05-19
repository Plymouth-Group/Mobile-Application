package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        supportActionBar?.hide()

        //button -----------------------------------------------------------------------------------
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        //txt --------------------------------------------------------------------------------------
        val email = findViewById<EditText>(R.id.txtEmailAddress)
        val pw = findViewById<EditText>(R.id.txtPassword)

        pw.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

        //firebase auth ----------------------------------------------------------------------------
        auth = Firebase.auth

        btnSignIn.setOnClickListener() {
            val emailAddress = "mindula1@gmail.com"
//            val password = pw.text.toString()
            val password = "mindula1"

            if (emailAddress.trim().isEmpty()) {
                Toast.makeText(
                    baseContext, "please enter email address",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (password.trim().isEmpty()) {
                    Toast.makeText(
                        baseContext, "please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    try {
                        auth.signInWithEmailAndPassword(emailAddress, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        baseContext, "Authentication Success..!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    home()
                                } else {
                                    Toast.makeText(
                                        baseContext, "Authentication Fail..!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } catch (e: Exception) {
                    }
                }
            }
        }

        btnSignUp.setOnClickListener() {
            signUp()
        }
    }

    private fun home() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun signUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}