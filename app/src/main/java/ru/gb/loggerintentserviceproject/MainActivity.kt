package ru.gb.loggerintentserviceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.gb.loggerintentserviseproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.buttonTest)
        btn.setOnClickListener {
            startService(Intent(this, TestService::class.java).apply {
                putExtra(
                    TEST_LOGGER_SERVICE_STRING_EXTRA,
                    getString(R.string.intent_message)
                )
            })
        }

    }
}