package com.jldemos.themeabledemo

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

class Utils {



    fun changeTheme(context: Context, darkMode: Boolean) {
        val theme = if (darkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        StoredPrefs().updateSavedTheme(context, theme)
        AppCompatDelegate.setDefaultNightMode(StoredPrefs().getAppTheme(context = context))
    }

    fun areWeRunningDarkTheme(context: Context): Boolean {
        val savedTheme = StoredPrefs().getAppTheme(context)
        return savedTheme == AppCompatDelegate.MODE_NIGHT_YES
    }


    /** =========================== ANDROID OPERATING SYSTEM DETECTION FUNCTIONS =========================== **/
    fun getAndroidVersion(): String{
        return when(detectOS()){
            OS.NOUGAT -> "Nougat (7)"
            OS.OREO -> "Oreo (8)"
            OS.PIE -> "Pie (9)"
            OS.TEN -> "TEN (10)"
            OS.NotSupported -> "NA"
        }
    }


    enum class OS {
        NOUGAT,
        OREO,
        PIE,
        TEN,
        NotSupported
    }

    fun detectOS(): OS {
        return when (Build.VERSION.SDK_INT) {
            NOUGAT,
            NOUGAT_ -> {
                OS.NOUGAT
            }
            OREO,
            OREO_ -> {
                OS.OREO
            }
            PIE -> {
                OS.PIE
            }
            TEN -> {
                OS.TEN
            }
            else -> {
                OS.NotSupported
            }
        }
    }

    //For above easier reading
    companion object {
        val NOUGAT = 24
        val NOUGAT_ = 25
        val OREO = 26
        val OREO_ = 27
        val PIE = 28
        val TEN = 29
    }
}