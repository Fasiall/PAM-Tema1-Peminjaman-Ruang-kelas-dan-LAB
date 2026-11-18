# Modul 7: Penyimpanan Lokal & Offline-First dengan SQLDelight

## Tema Proyek:
**Peminjaman Ruang Kelas dan Lab**

Modul ini berfokus pada implementasi arsitektur *offline-first* menggunakan database lokal **SQLDelight**. Aplikasi dirancang agar data ruangan dan riwayat peminjaman tetap dapat diakses meskipun tanpa koneksi internet (mode pesawat), mendukung observasi data secara reaktif, serta menyediakan fitur persistensi draf formulir peminjaman.

---

## Pembagian Tugas & Checklist Progres

> Gunakan checklist `[x]` untuk menandai tugas yang sudah selesai dikerjakan.

| Status | Anggota | Peran | Rincian Tugas | Output / File Utama |
| :---: | :--- | :--- | :--- | :--- |
| [x] | **Anggota 1** | **Skema Database Utama & Driver** | Menyiapkan konfigurasi SQLDelight di Gradle, file skema `.sq` untuk entitas utama (query `semua`, `byId`, `simpan`, `hapusSemua`), serta `DriverFactory` per platform. | `build.gradle.kts`, `RuangLab.sq`, `DriverFactory.kt` |
| [ ] | **Anggota 2** | **Driver per Platform** | Membuat deklarasi `expect` dan implementasi `actual` `DriverFactory` untuk Android serta iOS atau Desktop, lalu menyiapkan fungsi pembuat instance database lintas platform. | `DriverFactory.kt` (`commonMain`, `androidMain`, `iosMain`/`desktopMain`) |
| [ ] | **Anggota 3** | **Sumber Offline (Repository)** | Membangun lapisan sumber offline (seperti `SumberRuanganOffline`) dengan fungsi observasi database (`pantauSemua`) agar layar mengamati database secara reaktif, serta fungsi `segarkan` untuk menulis hasil jaringan ke database lokal. | `Sumber<Entitas>Offline.kt` |
| [ ] | **Anggota 4** | **Fitur Draf Form** | Membuat skema tabel Draf (`Draf.sq`) beserta query-nya, lalu mengimplementasikan logika penyimpanan draf otomatis saat form diisi dan pemulihan data draf saat form dibuka kembali. | `Draf.sq`, `Repository/Handler Draf Form` |
| [ ] | **Anggota 5** | **Integrasi ViewModel, UI Offline & QA** | Menghubungkan sumber offline dan fitur draf ke ViewModel untuk layar daftar dan form. Menambahkan pesan kecil di atas daftar saat aplikasi dibuka dalam mode pesawat agar daftar terakhir tetap tampil, lalu menguji seluruh skenario *offline-first*. | `ViewModel Daftar & Form`, `Banner/Pesan Offline UI`, `Laporan Uji Mode Pesawat` |

---

## Rincian Teknis & Checklist Deliverables

### 1. Anggota 1 — Skema Database Utama & Driver
- [x] **Konfigurasi Gradle**: Menambahkan plugin SQLDelight dan dependensi runtime di file `build.gradle.kts` (modul `shared`).
- [x] **Skema Entitas Utama (`RuangLab.sq`)**:
  - [x] Tabel entitas utama `RuangLabEntity`.
  - [x] Query `semua`: Mengambil semua data ruangan/lab.
  - [x] Query `byId`: Mengambil data spesifik berdasarkan `idRuang`.
  - [x] Query `simpan`: Melakukan *insert or replace* data ke database lokal.
  - [x] Query `hapusSemua`: Membersihkan seluruh baris pada tabel entitas utama.
- [x] **Driver per Platform & Inisialisasi Database**:
  - [x] Deklarasi `expect class DriverFactory` di `commonMain`.
  - [x] Implementasi `actual class DriverFactory` (`AndroidSqliteDriver`) di `androidMain`.
  - [x] Implementasi `actual class DriverFactory` (`NativeSqliteDriver`) di `iosMain`.
  - [x] Fungsi helper `createDatabase(driverFactory)` untuk inisialisasi `AppDatabase`.

### 3. Anggota 3 — Sumber Offline (Repository)
- [ ] **Observasi Reaktif**: Implementasi query `pantauSemua` menggunakan coroutine Flow SQLDelight (`asFlow().mapToList()`) agar UI terbarui secara otomatis.
- [ ] **Sinkronisasi Jaringan ke Lokal (`segarkan`)**: Mengambil data dari REST API Ktor / Mock API lalu memperbarui database lokal (*Single Source of Truth*).

### 4. Anggota 4 — Fitur Draf Form
- [ ] **Skema `Draf.sq`**: Membuat tabel database lokal untuk menampung field input formulir peminjaman.
- [ ] **Logika Draf**:
  - [ ] Menyimpan data draf formulir secara otomatis saat form diisi.
  - [ ] Memulihkan isi formulir dari draf saat halaman form dibuka kembali.
  - [ ] Membersihkan data draf setelah pengajuan form berhasil dikirim.

### 5. Anggota 5 — Integrasi ViewModel, UI Offline & QA
- [ ] **Integrasi ViewModel**: Menghubungkan Flow dari sumber offline dan logika draf ke UI State ViewModel.
- [ ] **UI Banner Offline**: Menampilkan banner/pesan di atas daftar saat perangkat offline/mode pesawat bahwa data yang tampil adalah data lokal terakhir.
- [ ] **Pengujian Skenario Offline-First**:
  - [ ] Uji saat online: data berhasil di-fetch dan tersimpan ke SQLite lokal.
  - [ ] Uji Mode Pesawat: daftar ruangan tetap tampil lengkap dari cache lokal.
  - [ ] Uji Form Draf: isi form, tutup aplikasi, buka kembali, dan pastikan draf terisi kembali.
