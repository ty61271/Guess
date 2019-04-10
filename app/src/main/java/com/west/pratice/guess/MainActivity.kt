package com.west.pratice.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "secret: ${secretNumber.secretNumber}")
    }

    fun check(view: View) {
        val n = number.text.toString().toInt()
        Log.d("MainActivity", "number : $n")
        val diff = secretNumber.validate(n)
        val message = when {
            diff < 0 -> "Bigger"
            diff > 0 -> "Smaller"
            else -> "Yes, you got it"
        }
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
