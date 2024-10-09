package com.primogemstudio.chat.data

import java.io.DataInput
import java.io.DataOutput

class PacketInt32 private constructor() : PacketDataType<Int32> {
    companion object {
        val INSTANCE = PacketInt32()
    }
    override fun getSize(): Int32 = 4
    override fun getName(): String = "int"
    override fun deserialize(raw: DataInput): Int32 = raw.readInt()
    override fun serialize(data: Any, out: DataOutput) { out.writeInt(data as Int32) }
}