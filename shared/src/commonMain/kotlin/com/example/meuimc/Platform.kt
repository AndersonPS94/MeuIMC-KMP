package com.example.meuimc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform