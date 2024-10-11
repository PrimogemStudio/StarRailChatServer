package com.primogemstudio.chat

import com.primogemstudio.chat.data.uni.PacketCombined
import com.primogemstudio.chat.data.uni.PacketInt32
import com.primogemstudio.chat.data.uni.PacketString
import com.primogemstudio.chat.data.wrap.DebugInputStream
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.DataInputStream
import java.net.ServerSocket
import java.net.Socket

class ChatServerMain {
    companion object {
        fun eventLoop(s: Socket) {
            val type = PacketCombined(listOf(PacketInt32.INSTANCE, PacketString.INSTANCE))
            while (true) {
                val r = s.getInputStream()
                if (r.available() != 0) {
                    println(type.deserialize(DataInputStream(DebugInputStream(r))))
                }
                Thread.sleep(500)
                println("Discovering!")
                s.getOutputStream().write(byteArrayOf(0x11, 0x45, 0x14))

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