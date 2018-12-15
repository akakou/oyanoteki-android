package com.akakou.sample.oyanoteki

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var serviceIntent : Intent? = null
    private var db : Firebase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceIntent = Intent(this, MainService::class.java)
        db = Firebase()
    }

    override fun onStart() {
        super.onStart()

        db!!.print(this)
    }

    override fun onPause() {
        super.onPause()
        startService(serviceIntent)

    }
}
