package com.west.pratice.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        setSupportActionBar(toolbar)
        Log.d(TAG, "Secret number:\t${secretNumber.secret}")

        fab.setOnClickListener {
            fab.setBaseAlerDialog(this, getString(R.string.replay_game), getString(R.string.are_you_sure)).apply {
                setPositiveButton(getString(R.string.ok)) { _, _ ->
                    secretNumber.reset()
                    counter.text = secretNumber.guessCount.toString()
                    ed_number.setText("")
                }
                setNeutralButton(getString(R.string.cancel), null)
                show()
            }
        }
        counter.text = secretNumber.guessCount.toString()
    }

    fun check(view: View) {

        secretNumber.guessCount++
        val number = ed_number.text.toString().toInt()
        val diff = number.validate(secretNumber.secret)
        val message = when {
            diff < 0 -> getString(R.string.bigger)
            diff > 0 -> getString(R.string.smaller)
            diff == 0 && secretNumber.guessCount < 3 -> getString(R.string.excellent) + secretNumber.secret
            else -> getString(R.string.good) + secretNumber.secret
        }
        counter.text = secretNumber.guessCount.toString()
        bt_ok.setBaseAlerDialog(this, getString(R.string.message), message).apply {
            setPositiveButton(getString(R.string.ok), null).show()
        }
    }
}
