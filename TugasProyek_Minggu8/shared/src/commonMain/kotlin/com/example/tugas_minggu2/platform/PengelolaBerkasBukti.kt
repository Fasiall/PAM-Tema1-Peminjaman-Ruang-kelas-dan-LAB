package com.example.tugas_minggu2.platform

import kotlinx.coroutines.flow.StateFlow

/**
 * Kontrak abstraksi fitur platform untuk pengelolaan berkas/foto bukti peminjaman ruangan.
 * Interface ini menjadi acuan implementasi platform nyata (Anggota 2 di Android, Anggota 3 di iOS/Desktop)
 * serta implementasi palsu (Anggota 1) untuk pengujian.
 */
interface PengelolaBerkasBukti {
    /**
     * Mengamati status izin akses fitur secara reaktif.
     */
    val statusIzin: StateFlow<StatusIzin>

    /**
     * Memeriksa status izin sistem saat ini tanpa memunculkan dialog.
     */
    suspend fun cekStatusIzin(): StatusIzin

    /**
     * Meminta izin kepada pengguna melalui dialog izin sistem.
     */
    suspend fun mintaIzin(): StatusIzin

    /**
     * Mengambil atau memilih berkas/foto bukti peminjaman jika izin diberikan.
     */
    suspend fun pilihBerkasBukti(): HasilAksiPlatform<BerkasBukti>

    /**
     * Membuka menu pengaturan aplikasi di sistem operasi saat izin ditolak permanen.
     */
    fun bukaPengaturanAplikasi()
}
