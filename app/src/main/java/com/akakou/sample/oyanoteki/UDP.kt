package com.akakou.sample.oyanoteki

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.io.IOException


object UDP {
    var ip = InetAddress.getByName("192.168.43.255")
    var port = 5000

    fun receive() : Byte {
        val socket = DatagramSocket(port)
        val buffer = ByteArray(1024)
        val packet = DatagramPacket(buffer, 0, buffer.size)

        val result = try {
            socket.receive(packet)
            buffer[0]
        } catch (e: IOException) {
            0.toByte()
        }

        socket.close()

        return result
    }
}