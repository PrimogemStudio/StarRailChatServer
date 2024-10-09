package com.primogemstudio.chat.data.uni

import java.io.DataInput
import java.io.DataOutput

interface PacketDataType<T: Any> {
    fun getSize(): Int
    fun getName(): String

    fun serialize(data: Any, out: DataOutput)
    fun deserialize(raw: DataInput): T
}