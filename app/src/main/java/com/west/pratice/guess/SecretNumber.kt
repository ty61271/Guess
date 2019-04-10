package com.west.pratice.guess

import java.util.*

class SecretNumber {
    val secretNumber = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int): Int {
        count++
        return number - secretNumber
    }
}

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secretNumber)
    println(secretNumber.validate(2))
    println("${secretNumber.validate(2)} , count : ${secretNumber.count}")
}