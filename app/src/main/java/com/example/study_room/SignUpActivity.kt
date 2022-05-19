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

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()


        //button -----------------------------------------------------------------------------------
        val btnRegBackSignIn = findViewById<Button>(R.id.btnRegBackSignIn)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        //txt --------------------------------------------------------------------------------------
        val registerUserName = findViewById<EditText>(R.id.txtRegisterUserName)
        val registerEmail = findViewById<EditText>(R.id.txtRegisterEmail)
        val registerPassword = findViewById<EditText>(R.id.txtRegisterPassword)

        registerPassword.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

        //firebase auth ----------------------------------------------------------------------------
        auth = Firebase.auth

        btnRegBackSignIn.setOnClickListener() {
            signIn()
        }

        btnRegister.setOnClickListener() {
            val regUserName = registerUserName.text.toString()
            val regEmail = registerEmail.text.toString()
            val regPassword = registerPassword.text.toString()

            if (regUserName.trim().isEmpty()) {
                Toast.makeText(
                    baseContext, "please enter user name",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (regEmail.trim().isEmpty()) {
                    Toast.makeText(
                        baseContext, "please enter email address",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (regPassword.trim().isEmpty()) {
                        Toast.makeText(
                            baseContext, "please enter password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        try {
                            auth.createUserWithEmailAndPassword(regEmail, regPassword)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            baseContext, "Register Success..!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        signRegister()
                                    } else {
                                        Toast.makeText(
                                            baseContext, "you have account, lets sign in now.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } catch (e: Exception) {
                        }
                    }
                }
            }
        }
    }

    private fun signIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun signRegister() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}