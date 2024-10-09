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
        val size = PacketInt.INSTANCE.deserialize(raw)
        val chars = CharArray(size)
        for (i in 0 ..< size) {
            chars[i] = raw.readChar()
        }

        return String(chars)
    }

    override fun serialize(data: Any, out: DataOutput) {
        val d = (data as String).toCharArray()
        PacketInt.INSTANCE.serialize(d.size, out)
        d.forEach { out.writeChar(it.code) }
    }
}