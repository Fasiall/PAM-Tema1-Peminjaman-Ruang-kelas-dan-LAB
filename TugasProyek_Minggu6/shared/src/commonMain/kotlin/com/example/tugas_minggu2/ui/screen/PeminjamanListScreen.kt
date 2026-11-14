package com.example.tugas_minggu2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas_minggu2.model.Peminjaman
import com.example.tugas_minggu2.model.RuangLab
import com.example.tugas_minggu2.model.StatusPeminjaman

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeminjamanListScreen() {
    val dummyPeminjamanList = listOf(
        Peminjaman("P001", "Ahmad Fauzi", "123140001", "2026-10-24", RuangLab("R01", "Lab Pemrograman 1", 40, "LAB", "PC Core i7, AC"), StatusPeminjaman.Diajukan, "Menunggu konfirmasi admin"),
        Peminjaman("P002", "Siti Aminah", "123140002", "2026-10-25", RuangLab("R02", "Ruang Kuliah A.2.1", 50, "Kelas", "Proyektor, Papan Tulis"), StatusPeminjaman.Disetujui, null),
        Peminjaman("P003", "Budi Santoso", "123140003", "2026-10-26", RuangLab("R03", "Lab Jaringan Komputer", 35, "LAB", "Router Cisco, Switch"), StatusPeminjaman.Ditolak, "Jadwal bentrok dengan perkuliahan"),
        Peminjaman("P004", "Dewi Lestari", "123140004", "2026-10-27", RuangLab("R04", "Ruang Kuliah B.1.2", 45, "Kelas", "AC, Sound System"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P005", "Eko Prasetyo", "123140005", "2026-10-28", RuangLab("R05", "Lab Multimedia", 30, "LAB", "MacBook, Kamera"), StatusPeminjaman.Disetujui, "Disetujui untuk kegiatan praktikum"),
        Peminjaman("P006", "Fitri Handayani", "123140006", "2026-10-29", RuangLab("R06", "Ruang Kuliah C.3.4", 60, "Kelas", "Kapasitas besar"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P007", "Gilang Ramadhan", "123140007", "2026-10-30", RuangLab("R07", "Lab Basis Data", 40, "LAB", "Server lokal, PC"), StatusPeminjaman.Disetujui, null),
        Peminjaman("P008", "Hesti Rahmawati", "123140008", "2026-10-31", RuangLab("R08", "Ruang Kuliah A.1.3", 50, "Kelas", "Standar"), StatusPeminjaman.Ditolak, "Ruangan sedang dalam perbaikan"),
        Peminjaman("P009", "Irfan Hakim", "123140009", "2026-11-01", RuangLab("R09", "Lab Sistem Embedded", 25, "LAB", "Arduino, Sensor Kit"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P010", "Joko Widodo", "123140010", "2026-11-02", RuangLab("R10", "Ruang Sidang Utama", 100, "Kelas", "Full AC, Mic Wireless"), StatusPeminjaman.Disetujui, "Acara seminar resmi")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Peminjaman Ruang Kelas & LAB") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Belum perlu berfungsi sesuai instruksi */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Peminjaman", tint = Color.White)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyPeminjamanList) { peminjaman ->
                PeminjamanItemCard(peminjaman = peminjaman)
            }
        }
    }
}

@Composable
fun PeminjamanItemCard(peminjaman: Peminjaman) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = peminjaman.ruangLab.namaRuangan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                StatusLabel(status = peminjaman.status)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Peminjam: ${peminjaman.namaPeminjam} (${peminjaman.nimPeminjam})",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Tanggal: ${peminjaman.tanggalPeminjaman}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (!peminjaman.catatanAdmin.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Catatan: ${peminjaman.catatanAdmin}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun StatusLabel(status: StatusPeminjaman) {
    val (text, backgroundColor) = when (status) {
        is StatusPeminjaman.Diajukan -> "Diajukan" to Color(0xFFFF9800)   // Oranye
        is StatusPeminjaman.Disetujui -> "Disetujui" to Color(0xFF4CAF50) // Hijau
        is StatusPeminjaman.Ditolak -> "Ditolak" to Color(0xFFE53935)     // Merah
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}