package com.example.tugas_minggu2.model

// 2. Entitas pendukung (data class kedua, yaitu Ruang atau LAB)[cite: 1]
data class RuangLab(
    val idRuang: String,
    val namaRuangan: String, // Contoh: "Lab Komputer 1" atau "Ruang Kuliah 3.2"
    val kapasitas: Int,
    val jenis: String, // Contoh: "LAB" atau "Kelas"
    val fasilitasDeskripsi: String? // Kolom nullable jika deskripsi fasilitas opsional[cite: 1]
)