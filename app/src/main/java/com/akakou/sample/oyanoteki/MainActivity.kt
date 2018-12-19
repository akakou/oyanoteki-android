package com.akakou.sample.oyanoteki

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*
import android.R.id.button3
import android.R.id.button2
import android.R.id.button1
import android.view.View


class MainActivity : AppCompatActivity() {
    private var serviceIntent : Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(this, MainService::class.java)
    }


    fun onClick(v: View) {
        when (v.getId()) {
            R.id.launch_button -> {
                startService(serviceIntent)
                finish()
            }

            R.id.door_button -> {
                val intent = Intent(this, DoorGraph::class.java)
                startActivity(intent);
            }

            R.id.front_button -> {
                val intent = Intent(this, FrontGraph::class.java)
                startActivity(intent);
            }
            R.id.log_button -> {
                val intent = Intent(this, LogActivity::class.java)
                startActivity(intent);
            }
        }
    }
}
