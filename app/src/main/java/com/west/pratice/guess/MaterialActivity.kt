package com.west.pratice.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    secretNumber.reset()
                    counter.text = secretNumber.count.toString()
                    number.setText("")
                }
                .setNeutralButton("Cancel", null)
                .show()
        }
        counter.text = secretNumber.count.toString()
    }

    fun check(view: View) {
        val n = number.text.toString().toInt()
        Log.d(TAG, "number : $n")
        val diff = secretNumber.validate(n)
        val message = when {
            diff < 0 -> getString(R.string.bigger)
            diff > 0 -> getString(R.string.smaller)
            else -> getString(R.string.yes_you_got_it)
        }
        counter.text = secretNumber.count.toString()
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }

}
