package com.primogemstudio.chat

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.ServerSocket
import java.net.Socket

fun eventLoop(s: Socket) {
    while (true) {
        val r = s.getInputStream()
        val a = r.available()
        if (a != 0) {
            val data = r.readNBytes(a)
            println(String(data, Charsets.UTF_8))
            s.getOutputStream().write(data)
        }
        Thread.sleep(1000)

        if (s.isClosed) {
            println("Socket $s is closed")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val server = ServerSocket(32767)
    val sockets = mutableListOf<Socket>()

    while (true) {
        val s = server.accept()
        sockets.add(s)
        println("Socket $s connected")
        GlobalScope.launch { eventLoop(s) }
    }
}