package ru.gb.loggerintentserviceproject

import android.app.Service
import android.content.Intent
import android.os.*

abstract class LoggerIntentService(val name: String) : Service() {

    private lateinit var looper: Looper
    private lateinit var loggerHandler: LoggerHandler

    override fun onBind(intent: Intent?): IBinder?  = null

    override fun onCreate() {
        super.onCreate()

        val thread = HandlerThread("Logger intent service $name")

        thread.start()
        looper = thread.looper
        loggerHandler = LoggerHandler(looper)

    }

    override fun onStart(intent: Intent?, startId: Int) {
        val msg: Message = loggerHandler.obtainMessage()
        msg.arg1 = startId
        msg.obj = intent
        loggerHandler.sendMessage(msg)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onStart(intent, startId)
        return START_NOT_STICKY
    }

    override fun onDestroy() = looper.quit()

    protected abstract fun onLoggerHandleIntent(intent: Intent?)


    inner class LoggerHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            onLoggerHandleIntent(msg.obj as Intent?)
            stopSelf(msg.arg1)
        }
    }


}
