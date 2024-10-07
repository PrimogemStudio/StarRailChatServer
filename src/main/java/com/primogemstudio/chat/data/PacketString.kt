package com.primogemstudio.chat.data

import org.fusesource.jansi.Ansi
import java.io.DataInput
import java.io.DataOutput
import java.io.InputStream

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

    @OptIn(ExperimentalStdlibApi::class)
    override fun printDebugMsg(raw: InputStream): String {
        val i = PacketInt.INSTANCE.printDebugOthMsg(raw)
        for (v in 0 ..< i) {
            val b = raw.readNBytes(2)
            b.forEach { print(Ansi.ansi().fgBrightYellow().a("0x${it.toHexString()} ").reset()) }
        }

        return "<debug mode>"
    }
}