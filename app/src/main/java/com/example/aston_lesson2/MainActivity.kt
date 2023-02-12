package com.example.aston_lesson2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.aston_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var localeList: LocaleListCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkLocale()

        binding.apply {

            btnEng.setOnClickListener {
                changeLocale(LOCALE_EN)
            }

            btnRus.setOnClickListener {
                changeLocale(LOCALE_RU)
            }

            btnNext.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun checkLocale(){
        if (getString(FIRST_TIME_MIGRATION) != STATUS_DONE) {
            getString(SELECTED_LANGUAGE)?.let { it ->
                localeList = LocaleListCompat.forLanguageTags(it)
                AppCompatDelegate.setApplicationLocales(localeList)
                putString(FIRST_TIME_MIGRATION, STATUS_DONE)
            }
        }
    }

    private fun changeLocale(locale:String){
        localeList = LocaleListCompat.forLanguageTags(locale)
        AppCompatDelegate.setApplicationLocales(localeList)
    }

    private fun putString(key: String, value: String) {
        val editor = getSharedPreferences(PREFERENCE_NAME, PREFERENCE_MODE).edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getString(key: String): String? {
        val preference = getSharedPreferences(PREFERENCE_NAME, PREFERENCE_MODE)
        return preference.getString(key, null)
    }

    companion object {
        const val PREFERENCE_NAME = "shared_preference"
        const val PREFERENCE_MODE = Context.MODE_PRIVATE
        const val FIRST_TIME_MIGRATION = "first_time_migration"
        const val SELECTED_LANGUAGE = "selected_language"
        const val STATUS_DONE = "status_done"
        const val LOCALE_RU = "ru"
        const val LOCALE_EN = "en"
    }
}