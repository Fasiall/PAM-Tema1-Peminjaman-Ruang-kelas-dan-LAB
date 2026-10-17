package com.example.pengingattugaskuliah

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import pengingattugaskuliah.shared.generated.resources.Res
import pengingattugaskuliah.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Pengingat Tugas Kuliah - ${getPlatform().name}")
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
//                    Image(painterResource(Res.zdrawable.compose_multiplatform), null)
                    Text("Nama Anggota Kelompok")
                    Text("Faisal H Sinambela (124140040")
                    Text("Hafidz Haqiqi (124140016")
                    Text("Muhammad Rofik Asidiqi (124140010")
                    Text("Stevan Immanuel Simbolon (124140130")
                    Text("Gede Valendra (124140142")
                }
            }
        }
    }
}