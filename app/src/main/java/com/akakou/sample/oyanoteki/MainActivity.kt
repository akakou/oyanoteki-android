package com.akakou.sample.oyanoteki

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.Legend
import android.view.View
import android.view.WindowManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import android.R.attr.entries
import android.graphics.Color
import android.widget.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import android.graphics.Color.LTGRAY
import java.text.SimpleDateFormat
import android.R.attr.entries
import android.R.attr.entries
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private var serviceIntent : Intent? = null
    private var db : Firebase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(this, MainService::class.java)
        db = Firebase()


        class CallBack() : FirebaseCallback {
            override fun callback(logList: MutableList<Log>) {
                val list = ArrayList<String>()

                for (data in logList) {
                    val type = data.type
                    val time = data.time

                    list.add("time:$time\n" +
                            "type:$type")
                }

                // ----------------------------------------------------------------------------------
                /*
                val adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, list)
                // ListViewにArrayAdapterを関連付け、データの表示を行う
                val listView = findViewById<ListView>(R.id.listView1)
                listView.adapter = adapter
                */
                // ----------------------------------------------------------------------------------


                launch(UI) {
                    var lineChart = findViewById<LineChart>(R.id.chart)
                    val context = this


                    // ----------------------------------

                    var dataSets = ArrayList<LineDataSet>()

                    var xValues = ArrayList<String>()
                    xValues.add("00:00")
                    xValues.add("04:00")
                    xValues.add("08:00")
                    xValues.add("12:00")
                    xValues.add("16:00")
                    xValues.add("20:00~")

                    var time0 = 0
                    var time4 = 0
                    var time8 = 0
                    var time12 = 0
                    var time16 = 0
                    var time20 = 0

                    val sdFormat = SimpleDateFormat("hh:mm:ss")
                    for (data in logList) {
                        if(data.type != "door") continue

                        val time = data.time
                        time!!.year = 2000
                        time!!.month = 1
                        time!!.date = 1


                        val compare = Date(2000, 1, 1, 4, 0)
                        val result = time.compareTo(compare)
                        if (result < 0) {
                            time0 += 1
                        }

                        else if (time.compareTo(Date(2000, 1, 1, 8, 0)) < 0) {
                            time4 += 1
                        }

                        else if (time.compareTo(Date(2000, 1, 1, 12, 0)) < 0) {
                            time8 += 1
                        }

                        else if (time.compareTo(Date(2000, 1, 1, 16, 0)) < 0) {
                            time12 += 1
                        }

                        else if (time.compareTo(Date(2000, 1, 1, 20, 0)) < 0) {
                            time16 += 1
                        }

                        else {
                            time20 += 1
                        }
                    }

                    // value
                    var value = ArrayList<Entry>()

                    value.add(Entry(time0.toFloat(), 0))
                    value.add(Entry(time4.toFloat(), 1))
                    value.add(Entry(time8.toFloat(), 2))
                    value.add(Entry(time12.toFloat(), 3))
                    value.add(Entry(time16.toFloat(), 4))
                    value.add(Entry(time20.toFloat(), 5))

                    var valueDataSet = LineDataSet(value, "door")
                    dataSets.add(valueDataSet);
                    lineChart.data = LineData(xValues, dataSets)

                    lineChart.invalidate();
                }

            }
        }

        db!!.sync(CallBack())


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
