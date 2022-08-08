package com.example.randomuser.domain

/**
 * Plain Kotlin data class that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see data.network for objects that parse or prepare network calls
 */

/**
 * RandomUser data to show at UI
 */

data class RandomUser(
    val photo: String,
    val name: String,
    val email: String,
    val birthday: String,
    val age: Int,
    val address: String,
    val phone: String,
    val password: String
)
