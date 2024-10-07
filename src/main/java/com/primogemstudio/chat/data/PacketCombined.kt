package com.primogemstudio.chat.data

import java.io.DataInput
import java.io.DataOutput
import java.io.InputStream

class PacketCombined(val data: List<PacketDataType<out Any>>): PacketDataType<List<Any>> {
    override fun getSize(): Int = -1
    override fun getName(): String = "Combined"
    override fun deserialize(raw: DataInput): List<Any> {
        return data.map { it.deserialize(raw) }
    }

    override fun printDebugMsg(raw: InputStream): List<Any> {
        data.map { it.printDebugMsg(raw) }
        return listOf()
    }

    override fun serialize(data: Any, out: DataOutput) {
        for (i in this.data.indices) {
            val r: Any? = (data as List<*>)[i]
            this.data[i].serialize(r!!, out)
        }
    }
}