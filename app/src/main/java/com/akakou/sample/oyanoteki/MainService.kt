package com.akakou.sample.oyanoteki

import android.content.Intent
import android.app.IntentService
import android.net.Uri

import android.widget.Toast
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.experimental.delay
import java.lang.Thread.sleep
import kotlin.concurrent.thread


/**
 * A constructor is required, and must call the super IntentService(String)
 * constructor with a name for the worker thread.
 */
class MainService : IntentService("MainService") {

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    override fun onHandleIntent(intent: Intent?) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.

        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(applicationContext, "あなたの部屋を守ります", Toast.LENGTH_SHORT).show()
        }

        while (true) {
            try {
                var data = UDP.receive()

                if (data == 'E'.toByte()) {
                    handler.post {
                        Toast.makeText(applicationContext, "奴が近づいている…\n気をつけろ…", Toast.LENGTH_SHORT).show()
                    }
                }

                if (data == 'D'.toByte()) {
                    val uri = Uri.parse("https://news.yahoo.co.jp/hl?c=bus")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }

            } catch (err: Exception) {
                handler.post {
                    Toast.makeText(applicationContext, "$err", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}