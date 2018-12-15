package com.akakou.sample.oyanoteki

import android.content.Context
import android.telecom.Call
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class Log(_type: String, _time: String) {
    val type: String? = _type
    val time: String? = _type
}


interface FirebaseCallback {
    fun callback(logList: MutableList<Log>) {

    }
}

class Firebase() {
    val db = FirebaseFirestore.getInstance()
    var logList :MutableList<Log> = mutableListOf()

    fun sync(callback: FirebaseCallback) {
        db.collection("logs")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val type: String = document.data.get("type").toString()
                            val time: String = document.data.get("data").toString()
                            val log = Log(type, time)
                            logList.add(log)
                        }

                        callback.callback(logList)
                    }
                }
    }

    fun print(context: Context) {
        class CallBack() : FirebaseCallback {
            override fun callback(logList: MutableList<Log>) {
                for (data in logList) {
                    val type = data.type
                    val time = data.time
                    Toast.makeText(context, "time:$time\ntype:$type", Toast.LENGTH_SHORT).show()
                }
            }
        }

        sync(CallBack())
    }
}