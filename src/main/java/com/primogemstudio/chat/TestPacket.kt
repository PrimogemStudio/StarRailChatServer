package com.primogemstudio.chat

import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

data class TestPacket(
    val test1: Int,
    val test2: String
) {
    fun send(s: Socket) {
        val o = DataOutputStream(s.getOutputStream())

        o.writeInt(test1)
        o.writeInt(test2.length)

        test2.toCharArray().forEach { o.writeChar(it.code) }
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun read(s: Socket): TestPacket {
            val rawData = s.getInputStream().readAllBytes()

            rawData.forEach { print(it.toHexString() + " ") }
            println()

            val i = DataInputStream(ByteArrayInputStream(rawData))

            val l1 = i.readInt()
            val l = i.readInt()
            val ls = mutableListOf<Char>()

            for (d in 0..< l) {
                ls.add(i.readChar())
            }

            return TestPacket(
                l1,
                String(ls.toCharArray())
            )
        }
    }
}