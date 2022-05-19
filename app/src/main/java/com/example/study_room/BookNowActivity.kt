package com.example.study_room

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class BookNowActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booknow)

        supportActionBar?.hide()

        database = Firebase.database.reference

        //button -----------------------------------------------------------------------------------
        val btnBookNow = findViewById<Button>(R.id.btnBookNow)
        val btnCancel = findViewById<Button>(R.id.btnStudentClear)
        val btnStudentAdd = findViewById<Button>(R.id.btnStudentAdd)

        val txtRoomNo = findViewById<EditText>(R.id.txtRoomNo)
        val txtStudentID = findViewById<EditText>(R.id.txtStudentID)
        val txtStudentName = findViewById<EditText>(R.id.txtStudentName)
        val txtStartArrangeTime = findViewById<EditText>(R.id.txtStartArrangeTime)
        val txtEndArrangeTime = findViewById<EditText>(R.id.txtEndArrangeTime)

        val tblBookNow = findViewById<TableLayout>(R.id.tblBookNow)


        btnBookNow.setOnClickListener() {

            val roomNo = txtRoomNo.text.toString()
            val floor = "1"
            val studentId = txtStudentID.text.toString()
            val studentName = txtStudentName.text.toString()
            val startArrangeTime = txtStartArrangeTime.text.toString()
            val endArrangeTime = txtEndArrangeTime.text.toString()
            val tableCount = tblBookNow.childCount.toString()

            if (roomNo.trim().isEmpty()) {
                Toast.makeText(
                    baseContext, "please enter room no",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (startArrangeTime.trim().isEmpty()) {
                    Toast.makeText(
                        baseContext, "please enter arrange start time",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (endArrangeTime.trim().isEmpty()) {
                        Toast.makeText(
                            baseContext, "please enter arrange end time",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (studentId.trim().isEmpty()) {
                            Toast.makeText(
                                baseContext, "please enter student id",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            if (studentName.trim().isEmpty()) {
                                Toast.makeText(
                                    baseContext, "please enter student name",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val num2 = 2
                                if (tableCount == num2.toString()) {
                                    delete()
                                    error()
                                } else {
                                    val num3 = 3
                                    if (tableCount == num3.toString()) {
                                        delete()
                                        error()
                                    } else {
                                        val num4 = 4
                                        if (tableCount == num4.toString()) {
                                            delete()
                                            error()
                                        } else {
                                            val num5 = 5
                                            if (tableCount == num5.toString()) {
                                                delete()
                                                error()
                                            } else {
                                                val num6 = 6
                                                if (tableCount == num6.toString()) {
                                                    val available = "Yes"
                                                    saveRoom(
                                                        roomNo,
                                                        floor,
                                                        available,
                                                        studentId,
                                                        studentName,
                                                        startArrangeTime,
                                                        endArrangeTime
                                                    )
                                                    success()
                                                } else {
                                                    error()
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        btnCancel.setOnClickListener() {
            txtStudentID.setText(null)
            txtStudentName.setText(null)
        }

        btnStudentAdd.setOnClickListener() {

            val stId = txtStudentID.text.toString()
            val stName = txtStudentName.text.toString()

            if (stId.trim().isEmpty()) {
                Toast.makeText(
                    baseContext, "please enter student Id",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (stName.trim().isEmpty()) {
                    Toast.makeText(
                        baseContext, "please enter student Name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    try {

                        val id = TextView(this)
                        val name = TextView(this)
                        val row = TableRow(this)
                        val lp: TableRow.LayoutParams =
                            TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                        row.setLayoutParams(lp)
                        id.text = stId
                        row.addView(id)
                        name.text = "                  " + stName
                        row.addView(name)
                        tblBookNow.addView(row, 1)

                        val studentId = txtStudentID.text.toString()
                        val studentName = txtStudentName.text.toString()

                        saveStudent(studentId, studentName)

                    } catch (e: Exception) {
                    }
                }
            }
        }
    }

    private fun saveRoom(
        roomNo: String,
        floor: String,
        available: String,
        studentId: String,
        studentName: String,
        startTime: String,
        endTime: String
    ) {
        val fire_store_db = FirebaseFirestore.getInstance()
        val student: MutableMap<String, Any> = HashMap()

        student["roomNo"] = roomNo
        student["floor"] = floor
        student["available"] = available
        student["studentId"] = studentId
        student["studentName"] = studentName
        student["startTime"] = startTime
        student["endTime"] = endTime

        try {
            fire_store_db.collection("room").add(student).addOnSuccessListener {
                Toast.makeText(this, "Booking Success", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Booking Fail", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {

        }
    }

    private fun saveStudent(studentId: String, studentName: String ) {
        val fire_store_db = FirebaseFirestore.getInstance()
        val student: MutableMap<String, Any> = HashMap()

        student["studentId"] = studentId
        student["studentName"] = studentName

        try {
            fire_store_db.collection("student").add(student).addOnSuccessListener {
                Toast.makeText(this, "Student Added Success", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Student Added Fail", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
        }
    }

    private fun success() {
        val intent = Intent(this, SuccessFullyActivity::class.java)
        startActivity(intent)
    }

    private fun error() {
        val intent = Intent(this, ErrorActivity::class.java)
        startActivity(intent)
    }

    private fun delete() {
        val fire_store_db = FirebaseFirestore.getInstance()
        fire_store_db.collection("student").document(" ")
            .delete()
            .addOnSuccessListener {
                println("================================================== ok")
            }
            .addOnFailureListener {
                println("================================================== no")

            }
    }
}
