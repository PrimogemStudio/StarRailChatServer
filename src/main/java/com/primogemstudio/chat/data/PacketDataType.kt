package com.primogemstudio.chat.data

import java.io.DataInput
import java.io.DataOutput
import java.io.InputStream

interface PacketDataType<T: Any> {
    fun getSize(): Int
    fun getName(): String

    fun serialize(data: Any, out: DataOutput)
    fun deserialize(raw: DataInput): T
    fun printDebugMsg(raw: InputStream): T
}