package com.example.aston_lesson2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
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
                putSharedString(preference)
                startMainActivity()
            }
        }
    }

    private fun putSharedString(preference:SharedPreferences){
        val editor = preference.edit()
        editor.putString(MainActivity.SHARED_TEXT,text)
        editor.apply()
    }

    private fun startMainActivity(){
        val intent = Intent(this@SecondActivity,MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK+Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MainActivity.SHARED_TEXT,text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        text = savedInstanceState.getString(MainActivity.SHARED_TEXT).toString()
    }
}