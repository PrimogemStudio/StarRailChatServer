package com.primogemstudio.chat

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args[0] == "--client") {
                ChatClientMain.main(args)
            }
            else ChatServerMain.main(args)
        }
    }
}