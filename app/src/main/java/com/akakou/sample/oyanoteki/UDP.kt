package com.akakou.sample.oyanoteki

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


object UDP {
    var ip = InetAddress.getByName("192.168.43.255")
    var port = 5000

    fun receive() : Char {
        // udpを受信して、文字列にして返す
        val socket = DatagramSocket(port)

        val buffer = ByteArray(1)
        val packet = DatagramPacket(buffer, buffer.size)

        socket.receive(packet)
        socket.close()

        return String(buffer)[0]
    }

    fun send(msg: String) {
        // udpで文字列を送信する
        val socket = DatagramSocket(port)

        val byte = msg.toByteArray();
        val packet = DatagramPacket(byte, byte.size, ip, port);

        socket.send(packet);
        socket.close()
    }
}