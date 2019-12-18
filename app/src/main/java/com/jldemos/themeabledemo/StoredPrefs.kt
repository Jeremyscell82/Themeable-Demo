package com.jldemos.themeabledemo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class StoredPrefs {


    fun updateSavedTheme(context: Context, theme: Int){
        writeIntToPref(context, themeKey, theme)
    }

    fun getAppTheme(context: Context): Int {
        return readIntFromPref(context, themeKey, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }


    /** START OF PRIVATE VALUES AND METHODS **/
    //SHARED PREFERENCE KEYS
    private val keyBase = "com.JLdesigns.newsapp."
    private val appKey = "${keyBase}StoredPrefKey"
    private val themeKey = "${keyBase}ThemeKey"

    private fun initEditor(context: Context): SharedPreferences.Editor {
        val sharedPreferences = context.getSharedPreferences(appKey, Context.MODE_PRIVATE)
        return sharedPreferences.edit()
    }

    private fun writeIntToPref(context: Context, KEY: String, value: Int) {
        val editor = initEditor(context)
        editor.putInt(KEY, value)
        editor.apply()
    }


    private fun readIntFromPref(context: Context, KEY: String, defaultValue: Int): Int {
        val sharedPreferences = context.getSharedPreferences(appKey, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(KEY, defaultValue)
    }

}