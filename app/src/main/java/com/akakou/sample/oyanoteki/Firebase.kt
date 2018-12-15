package com.akakou.sample.oyanoteki

import android.content.Context
import android.telecom.Call
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class Log(_type: String, _time: Date) {
    val type: String? = _type
    val time: Date? = _time
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
                            val timeStr: String = document.data.get("time").toString()

                            val sdFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
                            val time = sdFormat.parse(timeStr)

                            val log = Log(type, time)
                            logList.add(log)
                        }

                        callback.callback(logList)
                    }
                }
    }

    fun print(context: Context) {

    }
}