package com.nawm.android_labs.utils

import android.content.Context

class UserSettings(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        sharedPreferences.edit().putString("username", username).apply()
    }

    fun getUsername(): String {
        return sharedPreferences.getString("username", "default_user") ?: "default_user"
    }
}