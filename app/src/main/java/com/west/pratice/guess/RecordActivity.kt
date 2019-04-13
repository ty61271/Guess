package com.west.pratice.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER", -1)
        counter.text = count.toString()
        save.setOnClickListener {
            val nickname = ed_nickname.text.toString()
            getSharedPreferences("guess_homework", Context.MODE_PRIVATE)
                .edit()
                .putInt("RE_COUNT", count)
                .putString("RE_NICK", nickname)
                .apply()
            val intent = Intent().apply {
                putExtra("NICK", nickname)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
