package com.example.tugas_minggu2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform