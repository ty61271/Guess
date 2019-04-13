package com.west.pratice.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    private val REQUEST_RECORD: Int = 100
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        setSupportActionBar(toolbar)
        Log.d(TAG, "Secret number:\t${secretNumber.secret}")

        fab.setOnClickListener {
            replay()
        }
        counter.text = secretNumber.guessCount.toString()
        val shpf = getSharedPreferences("guess_homework", Context.MODE_PRIVATE)
        val nickname = shpf.getString("RE_NICK", null)
        val count = shpf.getInt("RE_COUNT", -1)
        Log.d(TAG, "onCreate: $count/$nickname")
    }

    private fun replay() {
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

    fun check(view: View) {

        secretNumber.guessCount++
        val number = ed_number.text.toString().toInt()
        val diff = number.validate(secretNumber.secret)
        counter.text = secretNumber.guessCount.toString()
        bt_ok.setBaseAlerDialog(this, getString(R.string.message), message(diff)).apply {
            setPositiveButton(getString(R.string.ok)) { _, _ ->
                if (diff == 0) {
                    val intent = Intent(this@MaterialActivity, RecordActivity::class.java).apply {
                        putExtra("COUNTER", secretNumber.guessCount)
                        startActivity(intent)
                    }
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            }
            show()
        }
    }

    fun message(diff: Int): String {
        return when {
            diff < 0 -> getString(R.string.bigger)
            diff > 0 -> getString(R.string.smaller)
            diff == 0 && secretNumber.guessCount < 3 -> getString(R.string.excellent) + secretNumber.secret
            else -> getString(R.string.good) + secretNumber.secret
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == REQUEST_RECORD && resultCode == Activity.RESULT_OK -> {
                val nickname=data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickname")
                replay()
            }
        }
    }
}
