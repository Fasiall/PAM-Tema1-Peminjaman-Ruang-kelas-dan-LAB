package com.example.pengingattugaskuliah

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform