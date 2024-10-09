package com.primogemstudio.chat

import com.primogemstudio.chat.data.wrap.PacketTest
import com.primogemstudio.chat.data.wrap.PacketTestData
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class ChatClientMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val soc = Socket()
            soc.connect(InetSocketAddress(InetAddress.getLocalHost(), 32767))

            val pck = PacketTest()
            pck.send(soc, PacketTestData(114514, "测试！abc"))
        }
    }
}