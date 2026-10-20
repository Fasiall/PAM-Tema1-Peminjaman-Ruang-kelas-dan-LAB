package com.example.tugas_minggu2.model

// 3. Satu sealed class untuk status sesuai bank proyek (Diajukan, Disetujui, Ditolak)
sealed class StatusPeminjaman {
    data object Diajukan : StatusPeminjaman()
    data object Disetujui : StatusPeminjaman()
    data object Ditolak : StatusPeminjaman()
}

// 1. Entitas utama peminjaman ruang kelas dan LAB[cite: 1]
data class Peminjaman(
    val idPeminjaman: String,
    val namaPeminjam: String,
    val nimPeminjam: String,
    val tanggalPeminjaman: String,
    val ruangLab: RuangLab, // Berelasi dengan entitas pendukung
    val status: StatusPeminjaman,
    val catatanAdmin: String? // 4. Kolom nullable (boleh kosong) untuk catatan[cite: 1]
)