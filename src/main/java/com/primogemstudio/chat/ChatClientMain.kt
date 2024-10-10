package com.primogemstudio.chat

import com.primogemstudio.chat.data.uni.PacketCombined
import com.primogemstudio.chat.data.uni.PacketInt32
import com.primogemstudio.chat.data.uni.PacketString
import com.primogemstudio.chat.data.wrap.DebugOutputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class ChatClientMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val soc = Socket()
            soc.connect(InetSocketAddress(InetAddress.getLocalHost(), 32767))

            val type = PacketCombined(listOf(PacketInt32.INSTANCE, PacketString.INSTANCE))
            type.serialize(listOf(114514, "测试！abc"), DataOutputStream(DebugOutputStream(soc.getOutputStream())))
        }
    }
}