package com.nawm.android_labs.utils

object RegistrationUtils {
    fun isEmailValid(email: String): Boolean {
        val emailRegexp = ".*@.*\\..*"
        val regexp = Regex(emailRegexp)
        return email.matches(regexp)
    }
}