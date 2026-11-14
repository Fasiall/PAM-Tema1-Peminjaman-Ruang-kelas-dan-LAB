package com.example.tugas_minggu2.model

data class RuangLab(
    val idRuang: String,
    val namaRuangan: String,
    val kapasitas: Int,
    val jenis: String,
    val fasilitasDeskripsi: String?
)