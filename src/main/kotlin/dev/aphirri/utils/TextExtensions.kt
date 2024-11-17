package dev.aphirri.utils

fun String.checkComm(): Boolean {
    var a: Boolean
    if (this[0] == '/') {
        a = false

    } else {
        a = true
    }
    return a
}