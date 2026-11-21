# Modul 8: Fitur Platform Khusus & Pengelolaan Izin (Permissions)

## Tema Proyek:
**Peminjaman Ruang Kelas dan Lab**

Modul ini berfokus pada integrasi fitur platform spesifik (seperti akses kamera/galeri untuk bukti peminjaman atau berkas, notifikasi, atau fitur perangkat keras lainnya) dengan mekanisme pengelolaan izin (*permissions*) yang ramah pengguna (*graceful degradation*) pada arsitektur Kotlin Multiplatform.

---

## Pembagian Tugas & Checklist Progres

> Gunakan tanda centang `[x]` pada kolom **Status** untuk menandai tugas peran yang telah selesai.

| Status | Anggota | Fokus Utama | Ringkasan Tugas |
| :---: | :--- | :--- | :--- |
| [x] | **Anggota 1** | **Kontrak commonMain & Versi Palsu** | Menulis interface/expect, implementasi *fake*, dan kontrak state hasil izin (diterima/ditolak) sebagai acuan tim. |
| [ ] | **Anggota 2** | **Implementasi Android & Izin Manifest** | Implementasi actual di `androidMain`, deklarasi izin di `AndroidManifest.xml`, dan pemicu izin saat tombol ditekan. |
| [ ] | **Anggota 3** | **Implementasi Platform Kedua & Info.plist** | Implementasi actual di `iosMain` (atau `desktopMain`), deklarasi izin di `Info.plist`, dan pemicu izin saat tombol ditekan. |
| [ ] | **Anggota 4** | **Integrasi UI & Penanganan Izin Ditolak** | Menghubungkan fitur ke UI, membangun fallback saat izin ditolak agar alur aplikasi tidak terputus, serta tombol alternatif (Buka Pengaturan). |
| [ ] | **Anggota 5** | **Tabel Pola README & Pengujian (QA)** | Mendokumentasikan tabel pola fitur platform di README, membuat unit test dengan implementasi palsu, dan menguji seluruh skenario. |

---

## Rincian Teknis & Checklist Deliverables

### 1. Anggota 1 — Kontrak commonMain & Versi Palsu - [Stevan]
- [✅] **Deklarasi Kontrak / Interface:**
  - [✅] Menulis `interface` atau deklarasi `expect` untuk fitur platform di `commonMain`.
  - [✅] Menentukan struktur return type / state hasil izin (misalnya: `Diterima`, `Ditolak`, `PerluRasional`) agar Anggota 2, 3, dan 4 memiliki acuan yang seragam.
- [✅] **Implementasi Fake (Versi Palsu):**
  - [✅] Membuat implementasi versi palsu (*fake/mock*) di `commonMain` untuk keperluan pengujian dan *preview* tanpa ketergantungan perangkat keras asli.

---

### 2. Anggota 2 — Implementasi Android & Izin Manifest
- [ ] **Implementasi Platform Android:**
  - [ ] Membuat implementasi `actual` (atau kelas implementasi interface) di `androidMain`.
- [ ] **Konfigurasi Izin Android:**
  - [ ] Menambahkan deklarasi izin yang relevan pada `AndroidManifest.xml`.
- [ ] **Alur Permintaan Izin:**
  - [ ] Mengatur alur permintaan izin (*runtime permission*) agar hanya dipicu secara kontekstual saat tombol fitur ditekan oleh pengguna (bukan saat aplikasi pertama kali dibuka).

---

### 3. Anggota 3 — Implementasi Platform Kedua & Info.plist
- [ ] **Implementasi Platform Kedua:**
  - [ ] Membuat implementasi `actual` kedua di `iosMain` (atau di `desktopMain` jika tim menargetkan desktop).
- [ ] **Konfigurasi Izin Platform Kedua:**
  - [ ] Menambahkan deskripsi penggunaan izin pada `Info.plist` (untuk target iOS) dengan pesan yang jelas bagi pengguna.
- [ ] **Alur Permintaan Izin:**
  - [ ] Memastikan dialog izin sistem hanya muncul saat aksi pengguna/tombol ditekan.

---

### 4. Anggota 4 — Integrasi UI & Jalur Saat Izin Ditolak
- [ ] **Integrasi Antarmuka (UI):**
  - [ ] Menghubungkan kontrak fitur platform dari `commonMain` ke layar UI yang relevan (misalnya formulir pengajuan peminjaman).
- [ ] **Graceful Degradation (Jalur Saat Ditolak):**
  - [ ] Memastikan alur utama aplikasi tetap dapat digunakan meskipun izin ditolak (aplikasi tidak *crash* atau *stuck*).
- [ ] **Umpan Balik Pengguna:**
  - [ ] Menampilkan pesan/banner singkat informatif saat izin ditolak.
  - [ ] Menyediakan tombol alternatif yang mengarahkan pengguna ke pengaturan sistem (misalnya tombol *Buka Pengaturan*).

---

### 5. Anggota 5 — Tabel Pola README & Pengujian
- [ ] **Dokumentasi Pola Fitur Platform:**
  - [ ] Mengisi tabel pola integrasi fitur platform pilihan kelompok (Langkah 10) pada tabel dokumentasi di bawah ini.
- [ ] **Unit Testing:**
  - [ ] Membuat rangkaian unit test memanfaatkan versi palsu (*fake*) dari Anggota 1.
- [ ] **Pengujian & Verifikasi Menyeluruh:**
  - [ ] Menguji skenario ketika izin diterima.
  - [ ] Menguji skenario ketika izin ditolak (verifikasi alur cadangan berfungsi).
  - [ ] Menguji pada kedua target platform dan melampirkan dokumentasi/bukti hasil uji.

---

## Tabel Pola Fitur Platform (Langkah 10)

| Komponen / Aspek | Keterangan / Implementasi |
| :--- | :--- |
| **Fitur yang Dipilih** | *(Contoh: Akses Galeri/Kamera untuk Bukti Peminjaman)* |
| **Kontrak `commonMain`** | *(Nama interface / expect class)* |
| **Implementasi Android** | *(Nama kelas actual / implementasi di androidMain)* |
| **Izin Android (`AndroidManifest.xml`)** | *(Tag `<uses-permission>` yang digunakan)* |
| **Implementasi iOS / Desktop** | *(Nama kelas actual / implementasi di platform kedua)* |
| **Izin iOS (`Info.plist`)** | *(Key deskripsi izin, misal: `NSPhotoLibraryUsageDescription`)* |
| **Perilaku Saat Izin Ditolak** | *(Alur cadangan dan pesan umpan balik ke pengguna)* |
