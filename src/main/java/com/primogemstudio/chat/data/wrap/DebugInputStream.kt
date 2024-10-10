package com.primogemstudio.chat.data.wrap

import java.io.InputStream

class DebugInputStream(private val `in`: InputStream): InputStream() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun read(): Int {
        return `in`.read().apply { print("0x${toUByte().toHexString()} ") }
    }
}