package com.example.tugas_minggu2.data

import com.example.tugas_minggu2.model.Peminjaman
import com.example.tugas_minggu2.model.RuangLab
import com.example.tugas_minggu2.model.StatusPeminjaman
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// 2. Sumber data palsu: Implementasi Sumber untuk entitas utama
interface SumberPeminjaman {
    fun ambilDaftarPeminjaman(): List<Peminjaman>
    fun pantauStatusPeminjaman(
        idPeminjaman: String,
        statusAkhir: StatusPeminjaman = StatusPeminjaman.Disetujui
    ): Flow<Peminjaman>
}

class SumberPeminjamanPalsu : SumberPeminjaman {

    private val daftarData = listOf(
        Peminjaman(
            idPeminjaman = "PMJ-001",
            namaPeminjam = "Faisal H. Sinambela",
            nimPeminjam = "124140020",
            tanggalPeminjaman = "2026-10-21",
            ruangLab = RuangLab(
                idRuang = "LAB-01",
                namaRuangan = "Lab Komputer 1",
                kapasitas = 35,
                jenis = "LAB",
                fasilitasDeskripsi = "35 PC, Proyektor, AC"
            ),
            status = StatusPeminjaman.Diajukan,
            catatanAdmin = null
        ),
        Peminjaman(
            idPeminjaman = "PMJ-002",
            namaPeminjam = "Stevan Immanuel Simbolon",
            nimPeminjam = "124140130",
            tanggalPeminjaman = "2026-10-22",
            ruangLab = RuangLab(
                idRuang = "RK-302",
                namaRuangan = "Ruang Kuliah 3.2",
                kapasitas = 50,
                jenis = "Kelas",
                fasilitasDeskripsi = null
            ),
            status = StatusPeminjaman.Diajukan,
            catatanAdmin = null
        )
    )

    override fun ambilDaftarPeminjaman(): List<Peminjaman> = daftarData

    // Flow perubahan status dengan delay
    override fun pantauStatusPeminjaman(
        idPeminjaman: String,
        statusAkhir: StatusPeminjaman
    ): Flow<Peminjaman> = flow {
        val dasar = daftarData.find { it.idPeminjaman == idPeminjaman }
            ?: throw IllegalArgumentException("Peminjaman dengan ID $idPeminjaman tidak ditemukan")

        // Status awal: Diajukan
        emit(dasar.copy(status = StatusPeminjaman.Diajukan, catatanAdmin = "Menunggu verifikasi admin"))

        delay(1000)

        // Perubahan status berikutnya (Disetujui / Ditolak)
        val catatan = when (statusAkhir) {
            is StatusPeminjaman.Disetujui -> "Disetujui oleh pengelola lab. Kunci dapat diambil di TU."
            is StatusPeminjaman.Ditolak -> "Ditolak: Ruangan sedang dalam pemeliharaan."
            is StatusPeminjaman.Diajukan -> "Status masih dalam peninjauan."
        }
        emit(dasar.copy(status = statusAkhir, catatanAdmin = catatan))
    }
}
