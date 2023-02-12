package com.example.aston_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aston_lesson2.databinding.ActivityMainBinding
import com.example.aston_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnReturn.setOnClickListener {
                Toast.makeText(this@SecondActivity, "Button is clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }
}