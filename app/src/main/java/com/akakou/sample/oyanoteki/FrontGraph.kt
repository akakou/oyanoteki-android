package com.akakou.sample.oyanoteki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.ArrayList

class FrontGraph : AppCompatActivity() {
    private var db : Firebase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_graph)

        db = Firebase()

        class CallBack() : FirebaseCallback {
            override fun callback(logList: MutableList<Log>) {

                launch(UI) {
                    var lineChart = findViewById<LineChart>(R.id.front_chart)

                    val graphData = GraphUtils.generate(logList, "front")
                    lineChart.data = LineData(graphData.xValues, graphData.dataSets)

                    lineChart.invalidate();
                }

            }
        }

        db!!.sync(CallBack())
    }
}
