package com.west.pratice.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
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
        Log.d(TAG, "onCreate: ")
        fab.setOnClickListener { view ->
            replay()
        }
        counter.text = secretNumber.count.toString()
        Log.d(TAG, "onCreat: ${secretNumber.secretNumber}")
        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG, "data: " + count + "/" + nick)
    }

    private fun replay() {
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d(TAG, "onPostResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
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
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java).apply {
                        putExtra("COUNTER", secretNumber.count)
                    }
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == REQUEST_RECORD && resultCode == Activity.RESULT_OK -> {
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickname")
                replay()
            }
        }
    }
}
