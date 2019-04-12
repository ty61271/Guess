package com.west.pratice.guess

import java.util.*

class SecretNumber {

    var secret = Random().nextInt(10) + 1
    var guessCount = 0

    fun reset(){
        secret=Random().nextInt(10) + 1
        guessCount=0
    }
}

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
    println(5.validate(secretNumber.secret))
}