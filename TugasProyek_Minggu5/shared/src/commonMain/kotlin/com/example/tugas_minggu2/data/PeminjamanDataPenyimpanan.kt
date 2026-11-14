package com.example.tugas_minggu2.data

import com.example.tugas_minggu2.model.Peminjaman
import com.example.tugas_minggu2.model.RuangLab
import com.example.tugas_minggu2.model.StatusPeminjaman

object PeminjamanDataPenyimpanan {
    val daftarPeminjaman: List<Peminjaman> = listOf(
        Peminjaman("P001", "Ahmad Fauzi", "123140001", "2026-10-24", RuangLab("R01", "Lab Pemrograman 1", 40, "LAB", "PC Core i7, AC, Proyektor"), StatusPeminjaman.Diajukan, "Menunggu konfirmasi admin"),
        Peminjaman("P002", "Siti Aminah", "123140002", "2026-10-25", RuangLab("R02", "Ruang Kuliah A.2.1", 50, "Kelas", "Proyektor, Papan Tulis"), StatusPeminjaman.Disetujui, null),
        Peminjaman("P003", "Budi Santoso", "123140003", "2026-10-26", RuangLab("R03", "Lab Jaringan Komputer", 35, "LAB", "Router Cisco, Switch, PC Client"), StatusPeminjaman.Ditolak, "Jadwal bentrok dengan perkuliahan"),
        Peminjaman("P004", "Dewi Lestari", "123140004", "2026-10-27", RuangLab("R04", "Ruang Kuliah B.1.2", 45, "Kelas", "AC, Sound System, Proyektor"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P005", "Eko Prasetyo", "123140005", "2026-10-28", RuangLab("R05", "Lab Multimedia", 30, "LAB", "MacBook, Kamera, Lighting"), StatusPeminjaman.Disetujui, "Disetujui untuk kegiatan praktikum"),
        Peminjaman("P006", "Fitri Handayani", "123140006", "2026-10-29", RuangLab("R06", "Ruang Kuliah C.3.4", 60, "Kelas", "Kapasitas besar, Sound System"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P007", "Gilang Ramadhan", "123140007", "2026-10-30", RuangLab("R07", "Lab Basis Data", 40, "LAB", "Server lokal, PC Client, AC"), StatusPeminjaman.Disetujui, null),
        Peminjaman("P008", "Hesti Rahmawati", "123140008", "2026-10-31", RuangLab("R08", "Ruang Kuliah A.1.3", 50, "Kelas", "Standar kelas"), StatusPeminjaman.Ditolak, "Ruangan sedang dalam perbaikan"),
        Peminjaman("P009", "Irfan Hakim", "123140009", "2026-11-01", RuangLab("R09", "Lab Sistem Embedded", 25, "LAB", "Arduino, Sensor Kit, Osiloskop"), StatusPeminjaman.Diajukan, null),
        Peminjaman("P010", "Joko Widodo", "123140010", "2026-11-02", RuangLab("R10", "Ruang Sidang Utama", 100, "Kelas", "Full AC, Mic Wireless, Proyektor Besar"), StatusPeminjaman.Disetujui, "Acara seminar resmi")
    )

    fun ambilBerdasarkanId(id: String): Peminjaman? {
        return daftarPeminjaman.find { it.idPeminjaman == id }
    }
}
