package com.akakou.sample.oyanoteki

import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*

class GraphData(_xValues: ArrayList<String>, _dataSets: ArrayList<LineDataSet> ) {
    val xValues : ArrayList<String> = _xValues
    val dataSets :  ArrayList<LineDataSet> = _dataSets
}

object GraphUtils {
    fun generate(list: MutableList<Log>, type: String): GraphData {

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
        for (data in list) {
            if(data.type != type) continue

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
        return GraphData(xValues, dataSets)
    }
}