package com.deluxan.medicine.utils.enum

/**
 * Created by Dilan M Deluxan on 28-Dec-20 AD at 8:08 PM
 */

enum class LoginPref {
    IS_LOGGED_IN, ACCESS_TOKEN;

    fun getDescription(): String {
        return when (this) {
            IS_LOGGED_IN -> Companion.IS_LOGGED_IN
            ACCESS_TOKEN -> Companion.ACCESS_TOKEN
            else -> "Undefined"
        }
    }

    companion object {
        private const val IS_LOGGED_IN = "IS_LOGGED_IN"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}