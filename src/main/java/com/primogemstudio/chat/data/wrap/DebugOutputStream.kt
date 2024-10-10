package com.primogemstudio.chat.data.wrap

import java.io.OutputStream

class DebugOutputStream(private val out: OutputStream): OutputStream() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun write(b: Int) {
        out.write(b)

        print("0x${b.toUByte().toHexString()} ")
    }
}