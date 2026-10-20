# Tugas Proyek Minggu 2 - Pengembangan Aplikasi Mobile (KMP)

Tema Proyek: **Sistem Peminjaman Ruang Kelas dan LAB**

Proyek ini adalah implementasi Kotlin Multiplatform (KMP) untuk pemodelan data domain, simulasi sumber data (fake data source), dan pengujian aliran perubahan status menggunakan Coroutines `Flow`.

---

## 1. Model Data Tema (`shared/src/commonMain/kotlin/com/example/tugas_minggu2/model/`)

1. **Entitas Utama (`Peminjaman.kt`)**:
   - `idPeminjaman: String`
   - `namaPeminjam: String`
   - `nimPeminjam: String`
   - `tanggalPeminjaman: String`
   - `ruangLab: RuangLab` (relasi ke entitas pendukung)
   - `status: StatusPeminjaman` (sealed class)
   - `catatanAdmin: String?` (nullable: hanya diisi jika admin memberi catatan saat verifikasi)

2. **Entitas Pendukung (`RuangLab.kt`)**:
   - `idRuang: String`
   - `namaRuangan: String`
   - `kapasitas: Int`
   - `jenis: String` (misal: "LAB" atau "Kelas")
   - `fasilitasDeskripsi: String?` (nullable: opsional jika detail fasilitas tidak dicantumkan)

3. **Sealed Class Status (`StatusPeminjaman`)**:
   - `data object Diajukan : StatusPeminjaman()`
   - `data object Disetujui : StatusPeminjaman()`
   - `data object Ditolak : StatusPeminjaman()`

---

## 2. Sumber Data Palsu (`shared/src/commonMain/kotlin/com/example/tugas_minggu2/data/SumberPeminjaman.kt`)

Implementasi sumber data (`SumberPeminjamanPalsu`) menyediakan simulasi asinkronus menggunakan Kotlin Coroutines `Flow` dan `delay(1000)`:

- `ambilDaftarPeminjaman(): List<Peminjaman>`: Mengembalikan daftar data tiruan awal peminjaman.
- `pantauStatusPeminjaman(idPeminjaman: String, statusAkhir: StatusPeminjaman): Flow<Peminjaman>`: Memancarkan status awal (`Diajukan`), memberi jeda simulasi verifikasi (`delay`), kemudian memancarkan perubahan status berikutnya (`Disetujui` atau `Ditolak`) beserta catatan admin.

---

## 3. Bukti Berjalan (Unit Test Konsol)

Unit test berada di `shared/src/commonTest/kotlin/com/example/tugas_minggu2/SumberPeminjamanTest.kt`.

### Cara Menjalankan:

```bash
./gradlew :shared:testAndroidHostTest --info
```

### Contoh Keluaran Konsol:

```text
SumberPeminjamanTest > ujiAliranPerubahanStatusDisetujui STANDARD_OUT
    === BUKTI BERJALAN: ALIRAN PERUBAHAN STATUS PEMINJAMAN (DISETUJUI) ===
    [KONSOL] ID: PMJ-001 | Peminjam: Faisal H. Sinambela | Ruang: Lab Komputer 1 | Status: Diajukan | Catatan: Menunggu verifikasi admin
    [KONSOL] ID: PMJ-001 | Peminjam: Faisal H. Sinambela | Ruang: Lab Komputer 1 | Status: Disetujui | Catatan: Disetujui oleh pengelola lab. Kunci dapat diambil di TU.
    === PENGUJIAN SELESAI: Status berhasil berubah dari Diajukan -> Disetujui ===

SumberPeminjamanTest > ujiAliranPerubahanStatusDitolak STANDARD_OUT
    === BUKTI BERJALAN: ALIRAN PERUBAHAN STATUS PEMINJAMAN (DITOLAK) ===
    [KONSOL] ID: PMJ-002 | Peminjam: Stevan Immanuel Simbolon | Ruang: Ruang Kuliah 3.2 | Status: Diajukan | Catatan: Menunggu verifikasi admin
    [KONSOL] ID: PMJ-002 | Peminjam: Stevan Immanuel Simbolon | Ruang: Ruang Kuliah 3.2 | Status: Ditolak | Catatan: Ditolak: Ruangan sedang dalam pemeliharaan.
    === PENGUJIAN SELESAI: Status berhasil berubah dari Diajukan -> Ditolak ===

BUILD SUCCESSFUL in 1m 23s
33 actionable tasks: 32 executed, 1 up-to-date
```

---

## 4. Pembagian Tugas Kelompok

| No  | Nama                         |    NIM    | Bagian yang Dikerjakan                                                                          |
| :-: | :--------------------------- | :-------: | :---------------------------------------------------------------------------------------------- |
|  1  | **Faisal H. Sinambela**      | 124140020 | Perancangan entitas utama `Peminjaman`, relasi data class, dan validasi tipe data               |
|  2  | **Hafidz Haqiqi**            | 124140016 | Perancangan entitas pendukung `RuangLab` dan penentuan kolom nullable                           |
|  3  | **Muhammad Rofik Asidiqi**   | 124140010 | Implementasi sealed class `StatusPeminjaman` (`Diajukan`, `Disetujui`, `Ditolak`)               |
|  4  | **Stevan Immanuel Simbolon** | 124140130 | Pembuatan antarmuka & kelas `SumberPeminjamanPalsu` dengan `Flow` dan `delay`                   |
|  5  | **Gede Valendra**            | 124140142 | Pembuatan unit test `SumberPeminjamanTest`, pengujian konsol, dan penyusunan dokumentasi README |
