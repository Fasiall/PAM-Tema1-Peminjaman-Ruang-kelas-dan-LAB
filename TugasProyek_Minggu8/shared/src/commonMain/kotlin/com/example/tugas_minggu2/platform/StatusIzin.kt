package com.example.tugas_minggu2.platform

/**
 * Representasi status izin (permission) akses fitur platform pada level multiplatform.
 * Digunakan sebagai acuan bersama oleh seluruh target platform (Android, iOS/Desktop) dan UI.
 */
sealed interface StatusIzin {
    /** Izin telah diberikan oleh pengguna */
    data object Diterima : StatusIzin

    /** Izin ditolak sementara oleh pengguna */
    data object Ditolak : StatusIzin

    /** Izin ditolak permanen (Do Not Ask Again), membutuhkan navigasi ke Pengaturan Sistem */
    data object DitolakPermanen : StatusIzin

    /** Izin belum pernah diminta kepada pengguna */
    data object BelumDiminta : StatusIzin
}

/**
 * Model data representasi berkas lampiran bukti peminjaman ruangan.
 */
data class BerkasBukti(
    val namaBerkas: String,
    val pathAtauUri: String,
    val ukuranBytes: Long = 0L
)

/**
 * Struktur return type generik untuk hasil eksekusi fitur platform.
 */
sealed interface HasilAksiPlatform<out T> {
    /** Aksi berhasil dilakukan dan menghasilkan data */
    data class Berhasil<T>(val data: T) : HasilAksiPlatform<T>

    /** Aksi tidak dapat dilanjutkan karena kendala izin */
    data class IzinDitolak(val statusIzin: StatusIzin) : HasilAksiPlatform<Nothing>

    /** Aksi dibatalkan oleh pengguna (misal menutup pemilih berkas tanpa memilih) */
    data object Dibatalkan : HasilAksiPlatform<Nothing>

    /** Terjadi kesalahan teknis saat menjalankan aksi */
    data class Gagal(val pesanError: String) : HasilAksiPlatform<Nothing>
}
