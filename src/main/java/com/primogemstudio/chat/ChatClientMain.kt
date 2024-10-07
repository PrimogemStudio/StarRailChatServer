package com.primogemstudio.chat

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
            val d = ByteArrayOutputStream()
            PacketInt.INSTANCE.serialize(114514, DataOutputStream(d))
            PacketString.INSTANCE.serialize("测试！123", DataOutputStream(d))
            println(d.toByteArray().toList())
            val i = ByteArrayInputStream(d.toByteArray())
            val i2 = ByteArrayInputStream(d.toByteArray())
            PacketInt.INSTANCE.printDebugMsg(i2)
            PacketString.INSTANCE.printDebugMsg(i2)
            println()

            println(PacketInt.INSTANCE.deserialize(DataInputStream(i)))
            println(PacketString.INSTANCE.deserialize(DataInputStream(i)))
        }
    }
}