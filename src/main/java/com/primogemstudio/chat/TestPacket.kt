package com.primogemstudio.chat

import com.primogemstudio.chat.data.PacketCombined
import com.primogemstudio.chat.data.PacketInt32
import com.primogemstudio.chat.data.PacketString
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

data class TestPacket(
    val test1: Int,
    val test2: String
) {
    fun send(s: Socket) {
        PacketCombined(listOf(PacketInt32.INSTANCE, PacketString.INSTANCE)).serialize(listOf(114514, "测试！abc"), DataOutputStream(s.getOutputStream()))
    }

    companion object {
        fun read(s: Socket): TestPacket {
            val l = PacketCombined(listOf(PacketInt32.INSTANCE, PacketString.INSTANCE)).deserialize(DataInputStream(s.getInputStream()))

            return TestPacket(
                l[0] as Int,
                l[1] as String
            )
        }
    }
}