package com.primogemstudio.chat.data.uni

import java.io.DataInput
import java.io.DataOutput

class PacketCombined(private val data: List<PacketDataType<out Any>>): PacketDataType<List<Any>> {
    override fun getSize(): Int = -1
    override fun getName(): String = "Combined"
    override fun deserialize(raw: DataInput): List<Any> {
        return data.map { it.deserialize(raw) }
    }

    override fun serialize(data: Any, out: DataOutput) {
        for (i in this.data.indices) {
            val r: Any? = (data as List<*>)[i]
            this.data[i].serialize(r!!, out)
        }
    }
}