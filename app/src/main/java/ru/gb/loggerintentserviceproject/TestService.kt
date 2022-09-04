package ru.gb.loggerintentserviceproject

import android.content.Intent
import android.util.Log

private const val TAG = "TestingLoggerTAG"
const val TEST_LOGGER_SERVICE_STRING_EXTRA = "TestLoggerServiceExtra"

class TestService(name: String = "Testing logger service") : LoggerIntentService(name) {

    override fun onLoggerHandleIntent(intent: Intent?) {
        createLogMessage(
            "onLoggerHandleIntent ${
                intent?.getStringExtra(
                    TEST_LOGGER_SERVICE_STRING_EXTRA
                )
            }"
        )
    }

    private fun createLogMessage(message: String) {
        Log.d(TAG, message)
    }
}