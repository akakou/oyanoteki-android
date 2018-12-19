package com.akakou.sample.oyanoteki

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class LogActivity : AppCompatActivity() {
    private var db : Firebase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        db = Firebase()

        class CallBack() : FirebaseCallback {
            override fun callback(logList: MutableList<Log>) {
                var list :ArrayList<String> = ArrayList()

                for (data in logList) {
                    val type = data.type
                    val time = data.time

                    list.add("time:$time\n" +
                            "type:$type")
                }

                val adapter = ArrayAdapter<String>(this@LogActivity, android.R.layout.simple_list_item_1, list)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = adapter
            }
        }

        db!!.sync(CallBack())
    }
}
