package dev.pankaj.cleanarchitecture.utils

fun Boolean.toInt() = if (this) 1 else 0
fun Int.toBoolean() = this > 0

fun String.isEmail() = matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"))
fun String.isMobileNumber() = matches(Regex("^[7-9][0-9]{9}$"))
fun String.isFullName() = matches(Regex("^[\\p{L} .'-]+$"))
