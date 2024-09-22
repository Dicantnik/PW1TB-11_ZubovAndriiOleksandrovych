package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val calc1: Button = findViewById(R.id.first_calc)
        val calc2: Button = findViewById(R.id.second_calc)

        calc1.setOnClickListener {
            val intent = Intent(this, FirstCalculator::class.java)
            startActivity(intent)
        }

        calc2.setOnClickListener {
            val intent = Intent(this, SecondCalculator::class.java)
            startActivity(intent)
        }
    }
}