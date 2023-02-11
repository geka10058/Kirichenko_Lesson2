package com.example.aston_lesson2

import android.content.Context
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
        const val PREFERENCE_NAME = R.string.shared_preference.toString()
        const val PREFERENCE_MODE = Context.MODE_PRIVATE
        const val FIRST_TIME_MIGRATION = R.string.first_time_migration.toString()
        const val SELECTED_LANGUAGE = R.string.selected_language.toString()
        const val STATUS_DONE = R.string.status_done.toString()
        const val LOCALE_RU = "ru"
        const val LOCALE_EN = "en"
    }
}