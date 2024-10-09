package com.primogemstudio.chat.data.wrap

import java.net.Socket

interface Packet<T: Any> {
    fun send(s: Socket, data: T)
    fun read(s: Socket): T
}