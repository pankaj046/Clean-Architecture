package dev.pankaj.cleanarchitecture.data.local.prefmanager

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesUtil @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {

    fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        editor.remove(key).apply()
    }

    fun clear() {
        editor.clear().apply()
    }

    fun containKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }
}
