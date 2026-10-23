package com.example.tugas_minggu2.model
sealed class StatusPeminjaman {
    object Diajukan : StatusPeminjaman()
    object Disetujui : StatusPeminjaman()
    object Ditolak : StatusPeminjaman()
}
data class Peminjaman(
    val idPeminjaman: String,
    val namaPeminjam: String,
    val nimPeminjam: String,
    val tanggalPeminjaman: String,
    val ruangLab: RuangLab,
    val status: StatusPeminjaman,
    val catatanAdmin: String?
)