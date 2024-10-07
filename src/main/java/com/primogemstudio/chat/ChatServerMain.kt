package com.primogemstudio.chat

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.ServerSocket
import java.net.Socket

class ChatServerMain {
    companion object {
        fun eventLoop(s: Socket) {
            while (true) {
                val r = s.getInputStream()
                val a = r.available()
                if (a != 0) {
                    println(TestPacket.read(s))
                }
                Thread.sleep(1000)

                if (s.isClosed) {
                    println("Socket $s is closed")
                }
            }
        }

        @JvmStatic
        @OptIn(DelicateCoroutinesApi::class)
        fun main(args: Array<String>) {
            val server = ServerSocket(32767)
            val sockets = mutableListOf<Socket>()

            while (true) {
                val s = server.accept()
                sockets.add(s)
                println("Socket $s connected")
                GlobalScope.launch { eventLoop(s) }
            }
        }
    }
}