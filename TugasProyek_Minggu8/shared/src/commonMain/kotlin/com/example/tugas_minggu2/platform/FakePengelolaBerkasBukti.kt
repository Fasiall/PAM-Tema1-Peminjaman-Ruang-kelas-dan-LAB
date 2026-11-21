package com.example.tugas_minggu2.platform

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Implementasi versi palsu (fake/mock) dari [PengelolaBerkasBukti] di commonMain.
 * Digunakan untuk:
 * 1. Pratinjau (Preview) tampilan UI tanpa emulator fisik oleh Anggota 4.
 * 2. Rangkaian Unit Testing di commonTest oleh Anggota 5.
 */
class FakePengelolaBerkasBukti(
    statusAwal: StatusIzin = StatusIzin.BelumDiminta,
    private var simulasiHasilBerkas: BerkasBukti? = BerkasBukti(
        namaBerkas = "surat_izin_peminjaman_lab.pdf",
        pathAtauUri = "fake://storage/documents/surat_izin_peminjaman_lab.pdf",
        ukuranBytes = 256_000L
    )
) : PengelolaBerkasBukti {

    private val _statusIzin = MutableStateFlow(statusAwal)
    override val statusIzin: StateFlow<StatusIzin> = _statusIzin.asStateFlow()

    /** Variabel pelacak apakah tombol/fungsi buka pengaturan dipanggil */
    var jumlahBukaPengaturanDipanggil: Int = 0
        private set

    /** Sakelar simulasi: apakah izin sistem akan diterima atau ditolak */
    var apakahBisaMemberiIzin: Boolean = true

    /** Sakelar simulasi: apakah pengguna membatalkan dialog pemilihan berkas */
    var apakahPenggunaMembatalkanPilihan: Boolean = false

    fun aturStatusIzin(status: StatusIzin) {
        _statusIzin.value = status
    }

    override suspend fun cekStatusIzin(): StatusIzin {
        return _statusIzin.value
    }

    override suspend fun mintaIzin(): StatusIzin {
        val statusBaru = if (apakahBisaMemberiIzin) {
            StatusIzin.Diterima
        } else {
            StatusIzin.Ditolak
        }
        _statusIzin.value = statusBaru
        return statusBaru
    }

    override suspend fun pilihBerkasBukti(): HasilAksiPlatform<BerkasBukti> {
        return when (_statusIzin.value) {
            is StatusIzin.Diterima -> {
                if (apakahPenggunaMembatalkanPilihan) {
                    HasilAksiPlatform.Dibatalkan
                } else {
                    val berkas = simulasiHasilBerkas
                    if (berkas != null) {
                        HasilAksiPlatform.Berhasil(berkas)
                    } else {
                        HasilAksiPlatform.Gagal("Berkas tiruan tidak ditemukan")
                    }
                }
            }
            is StatusIzin.Ditolak, is StatusIzin.DitolakPermanen, is StatusIzin.BelumDiminta -> {
                HasilAksiPlatform.IzinDitolak(_statusIzin.value)
            }
        }
    }

    override fun bukaPengaturanAplikasi() {
        jumlahBukaPengaturanDipanggil++
    }
}
