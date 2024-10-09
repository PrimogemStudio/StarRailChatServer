package com.primogemstudio.chat.data

import org.fusesource.jansi.Ansi
import java.io.DataInput
import java.io.DataOutput
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class PacketInt private constructor() : PacketDataType<Int> {
    companion object {
        val INSTANCE = PacketInt()
    }
    override fun getSize(): Int = 4
    override fun getName(): String = "int"
    override fun deserialize(raw: DataInput): Int = raw.readInt()
    override fun serialize(data: Any, out: DataOutput) { out.writeInt(data as Int) }

    @OptIn(ExperimentalStdlibApi::class)
    fun printDebugOthMsg(raw: InputStream): Int {
        val i = raw.readNBytes(4)

        i.forEach {
            print(Ansi.ansi().fgYellow().a("0x${it.toHexString()} ").reset())
        }

        return ByteBuffer.wrap(i).order(ByteOrder.BIG_ENDIAN).getInt();
    }
}