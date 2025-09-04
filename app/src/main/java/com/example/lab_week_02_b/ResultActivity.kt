package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.IllegalArgumentException

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent != null) {
            // ✅ Ambil langsung pakai string key (harus sama dengan yang di MainActivity)
            val colorCode = intent.getStringExtra("COLOR_KEY")

            val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            } catch (ex: IllegalArgumentException) {
                Intent().let { errorIntent ->
                    errorIntent.putExtra("ERROR_KEY", true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                    return
                }
            }

            val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(
                R.string.color_code_result_message,
                colorCode?.uppercase()
            )
        }

        // ✅ Back button: cukup finish() biar balik ke MainActivity
        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}