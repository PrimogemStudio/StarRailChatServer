package com.primogemstudio.chat

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args[0] == "--client") {
                ChatClientMain.main(args)
            }
            else if (args[0] == "--server") ChatServerMain.main(args)
        }
    }
}