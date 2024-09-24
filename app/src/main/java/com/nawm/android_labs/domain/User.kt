package com.nawm.android_labs.domain

import java.io.Serializable

data class User(val userName: String, val email: String, val password: String) : Serializable