package com.primogemstudio.chat

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class ChatClientMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val soc = Socket()
            soc.connect(InetSocketAddress(InetAddress.getLocalHost(), 32767))

            TestPacket(114514, "测试！abc").send(soc)
        }
    }
}