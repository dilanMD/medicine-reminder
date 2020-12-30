package com.deluxan.medicine.utils.enum

/**
 * Created by Dilan M Deluxan on 28-Dec-20 AD at 7:57 PM
 */

enum class SharedPrefType {
    LOGIN_PREF;

    fun getDescription(): String {
        return when (this) {
            LOGIN_PREF -> Companion.LOGIN_PREF
            else -> "Undefined"
        }
    }

    companion object {
        private const val LOGIN_PREF = "LOGIN_PREF"
    }
}