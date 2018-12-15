package com.akakou.sample.oyanoteki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            launch (UI) {
                Toast.makeText(this@MainActivity, "hogehoge", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
