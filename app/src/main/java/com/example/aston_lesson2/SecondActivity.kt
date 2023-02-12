package com.example.aston_lesson2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.aston_lesson2.databinding.ActivityMainBinding
import com.example.aston_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = getSharedPreferences(
            MainActivity.PREFERENCE_NAME,
            MainActivity.PREFERENCE_MODE
        )

        binding.apply {

            etTitle.addTextChangedListener {
                text = it.toString()
            }

            btnReturn.setOnClickListener {
                val editor = preference.edit()
                editor.putString(MainActivity.SHARED_TEXT,text)
                editor.apply()
                val intent = Intent(this@SecondActivity,MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK+Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}