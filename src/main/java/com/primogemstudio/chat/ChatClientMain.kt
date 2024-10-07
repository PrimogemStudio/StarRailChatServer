package com.primogemstudio.chat

import com.primogemstudio.chat.data.PacketCombined
import com.primogemstudio.chat.data.PacketInt
import com.primogemstudio.chat.data.PacketString
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

class ChatClientMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /*val soc = Socket()
            soc.connect(InetSocketAddress(InetAddress.getLocalHost(), 32767))

            TestPacket(114514, "测试！abc").send(soc)*/

            val p = PacketCombined(listOf(PacketInt.INSTANCE, PacketString.INSTANCE))

            val d = ByteArrayOutputStream()
            p.serialize(listOf(114514, "测试！abc"), DataOutputStream(d))
            println(d.toByteArray().toList())
            val i = ByteArrayInputStream(d.toByteArray())
            val i2 = ByteArrayInputStream(d.toByteArray())
            p.printDebugMsg(i)

            println()
            println(p.deserialize(DataInputStream(i2)))
        }
    }
}