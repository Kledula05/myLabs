package com.Ustenko

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Ustenko.data.ListGenerator
import com.Ustenko.domain.ListProcessor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listText = findViewById<TextView>(R.id.listText)
        val zInput = findViewById<EditText>(R.id.zInput)
        val button = findViewById<Button>(R.id.button)
        val resultText = findViewById<TextView>(R.id.resultText)

        val list = ListGenerator.generate()
        listText.text = "Исходный список: " + list.joinToString(", ")

        button.setOnClickListener {
            resultText.text = ListProcessor.getResult(list, zInput.text.toString())
        }
    }
}
