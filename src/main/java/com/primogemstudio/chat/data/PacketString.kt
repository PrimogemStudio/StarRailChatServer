package com.primogemstudio.chat.data

import java.io.DataInput
import java.io.DataOutput

class PacketString private constructor() : PacketDataType<String> {
    companion object {
        val INSTANCE = PacketString()
    }

    override fun getSize(): Int = -1
    override fun getName(): String = "String"
    override fun deserialize(raw: DataInput): String {
        return raw.readUTF()
    }

    override fun serialize(data: Any, out: DataOutput) {
        out.writeUTF(data as String)
    }
}