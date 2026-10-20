package com.example.tugas_minggu2

import com.example.tugas_minggu2.data.SumberPeminjamanPalsu
import com.example.tugas_minggu2.model.StatusPeminjaman
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

// 3. Bukti berjalan: Test kecil yang mencetak perubahan status ke konsol
class SumberPeminjamanTest {

    private val sumber = SumberPeminjamanPalsu()

    @Test
    fun ujiAliranPerubahanStatusDisetujui() = runTest {
        println("=== BUKTI BERJALAN: ALIRAN PERUBAHAN STATUS PEMINJAMAN (DISETUJUI) ===")
        val riwayat = mutableListOf<StatusPeminjaman>()

        sumber.pantauStatusPeminjaman("PMJ-001", StatusPeminjaman.Disetujui).collect { data ->
            riwayat.add(data.status)
            println(
                "[KONSOL] ID: ${data.idPeminjaman} | Peminjam: ${data.namaPeminjam} " +
                    "| Ruang: ${data.ruangLab.namaRuangan} | Status: ${data.status} | Catatan: ${data.catatanAdmin ?: "-"}"
            )
        }

        assertEquals(2, riwayat.size)
        assertEquals(StatusPeminjaman.Diajukan, riwayat[0])
        assertEquals(StatusPeminjaman.Disetujui, riwayat[1])
        println("=== PENGUJIAN SELESAI: Status berhasil berubah dari Diajukan -> Disetujui ===\n")
    }

    @Test
    fun ujiAliranPerubahanStatusDitolak() = runTest {
        println("=== BUKTI BERJALAN: ALIRAN PERUBAHAN STATUS PEMINJAMAN (DITOLAK) ===")
        val riwayat = mutableListOf<StatusPeminjaman>()

        sumber.pantauStatusPeminjaman("PMJ-002", StatusPeminjaman.Ditolak).collect { data ->
            riwayat.add(data.status)
            println(
                "[KONSOL] ID: ${data.idPeminjaman} | Peminjam: ${data.namaPeminjam} " +
                    "| Ruang: ${data.ruangLab.namaRuangan} | Status: ${data.status} | Catatan: ${data.catatanAdmin ?: "-"}"
            )
        }

        assertEquals(2, riwayat.size)
        assertEquals(StatusPeminjaman.Diajukan, riwayat[0])
        assertEquals(StatusPeminjaman.Ditolak, riwayat[1])
        println("=== PENGUJIAN SELESAI: Status berhasil berubah dari Diajukan -> Ditolak ===\n")
    }
}
