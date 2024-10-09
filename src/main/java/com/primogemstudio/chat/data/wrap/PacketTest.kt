package com.primogemstudio.chat.data.wrap

import com.primogemstudio.chat.data.uni.Int32
import com.primogemstudio.chat.data.uni.PacketCombined
import com.primogemstudio.chat.data.uni.PacketInt32
import com.primogemstudio.chat.data.uni.PacketString
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class PacketTest: Packet<PacketTestData> {
    companion object {
        val type = PacketCombined(listOf(PacketInt32.INSTANCE, PacketString.INSTANCE))
    }

    override fun send(s: Socket, data: PacketTestData) {
        type.serialize(listOf(data.test1, data.test2), DataOutputStream(s.getOutputStream()))
    }

    override fun read(s: Socket): PacketTestData {
        val i = type.deserialize(DataInputStream(s.getInputStream()))
        return PacketTestData(i[0] as Int32, i[1] as String)
    }
}

data class PacketTestData(
    val test1: Int32,
    val test2: String
)