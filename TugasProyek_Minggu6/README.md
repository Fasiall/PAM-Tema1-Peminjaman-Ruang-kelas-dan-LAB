This is a Kotlin Multiplatform project targeting Android, iOS.

- [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

- [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

## Modul 6: Networking REST API dengan Ktor

### Tema Proyek:
**Peminjaman Ruang Kelas dan Lab**

---

### Tahap 1: Pilih Server & Mock API
Data entitas utama (ruang kelas dan laboratorium) disiapkan menggunakan Mock API berbasis JSON.

- **File Mock JSON**: `mock_ruangan.json`
- **Konfigurasi Base URL**: `ApiConfig.kt` (`com.example.tugas_minggu2.data.network`)
- **Format Respons**: `application/json`

#### Spesifikasi Endpoint:
- **Endpoint**: `GET /ruangan`
- **Deskripsi**: Mengambil seluruh daftar ruang kelas dan laboratorium beserta kapasitas dan status ketersediaannya.
- **Struktur Field Respons**:
  - `id` (`String`): Identitas unik ruangan (contoh: `"R001"`)
  - `nama_ruangan` (`String`): Nama ruang kelas atau laboratorium
  - `tipe` (`String`): Kategori ruangan (`"Kelas"` atau `"Lab"`)
  - `kapasitas` (`Int`): Daya tampung ruangan
  - `status_ketersediaan` (`String`): Status pemakaian ruangan (`"Tersedia"` atau `"Dipakai"`)
  - `url_gambar` (`String`): URL gambar ruangan

#### Contoh Respons JSON:
```json
[
  {
    "id": "R001",
    "nama_ruangan": "Ruang Kelas GK1-101",
    "tipe": "Kelas",
    "kapasitas": 40,
    "status_ketersediaan": "Tersedia",
    "url_gambar": ""
  },
  {
    "id": "R002",
    "nama_ruangan": "Laboratorium Komputer Dasar",
    "tipe": "Lab",
    "kapasitas": 35,
    "status_ketersediaan": "Dipakai",
    "url_gambar": ""
  }
]
```

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
