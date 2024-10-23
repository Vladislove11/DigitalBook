package com.example.digitalbook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var textTV: TextView
    private lateinit var textET: EditText
    private lateinit var buttonLoadBTN: Button

    private var database = Database("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        textTV = findViewById(R.id.textTV)
        buttonLoadBTN = findViewById(R.id.buttonLoadBTN)

        database.text = loadBook().joinToString()

        buttonLoadBTN.setOnClickListener {
            textET.setText(database.text)
        }
    }

    private fun loadBook(): List<String>{
        var tmpSet = mutableSetOf<String>()

        try {
            val file = this.assets.open("SmallStory.txt").bufferedReader().use { it.readText() }
            var result: List<String> = file
                .replace(",", "")
                .replace(".", "")
                .replace("—", "")
                .replace("?","")
                .split(" ")

            repeat(result.size/4){
                tmpSet.add(result.random())
            }

        } catch (e: IOException){
            e.printStackTrace()
        }
        return tmpSet.toList()
    }
}