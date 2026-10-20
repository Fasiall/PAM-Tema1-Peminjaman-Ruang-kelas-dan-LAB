<div align="center">
  <h1>🏫 KELOMPOK T - PAM KMP</h1>
  <h3>Sistem Peminjaman Ruang Kelas dan LAB</h3>
  <p><em>Solusi digital efisien untuk manajemen dan peminjaman fasilitas kampus secara multiplatform.</em></p>

<svg class="waves" xmlns="http://www.w3.org/2000/svg" viewBox="0 24 150 28" preserveAspectRatio="none">
    <defs>
      <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
    </defs>
    <g class="parallax">
      <use href="#gentle-wave" x="48" y="0" fill="rgba(0, 119, 182, 0.7)" />
      <use href="#gentle-wave" x="48" y="3" fill="rgba(0, 150, 199, 0.5)" />
      <use href="#gentle-wave" x="48" y="5" fill="rgba(72, 202, 228, 0.3)" />
      <use href="#gentle-wave" x="48" y="7" fill="rgba(202, 240, 248, 0.5)" />
    </g>
  </svg>
</div>

<style>
  .waves {
    position: relative;
    width: 100%;
    height: 15vh;
    min-height: 40px;
    max-height: 80px;
  }
  .parallax > use {
    animation: move-forever 25s cubic-bezier(.55,.5,.45,.5) infinite;
  }
  .parallax > use:nth-child(1) {
    animation-delay: -2s;
    animation-duration: 7s;
  }
  .parallax > use:nth-child(2) {
    animation-delay: -3s;
    animation-duration: 10s;
  }
  .parallax > use:nth-child(3) {
    animation-delay: -4s;
    animation-duration: 13s;
  }
  .parallax > use:nth-child(4) {
    animation-delay: -5s;
    animation-duration: 20s;
  }
  @keyframes move-forever {
    0% { transform: translate3d(-90px,0,0); }
    100% { transform: translate3d(85px,0,0); }
  }
</style>

---

## 👥 Anggota Kelompok

| No | Nama | NIM | Peran |
|:--:|:-----|:---:|:-----:|
| 1 | **Faisal H. Sinambela** | 124140020 | Ketua |
| 2 | **Hafidz Haqiqi** | 124140016 | Anggota |
| 3 | **Muhammad Rofik Asidiqi** | 124140010 | Anggota |
| 4 | **Stevan Immanuel Simbolon** | 124140130 | Anggota |
| 5 | **Gede Valendra** | 124140142 | Anggota |

---

## 📌 Deskripsi Proyek
Proyek ini dikembangkan menggunakan **Kotlin Multiplatform (KMP)** untuk membangun sistem peminjaman ruang kelas dan laboratorium terintegrasi. Dilengkapi dengan pengelolaan status peminjaman (Diajukan, Disetujui, Ditolak) serta pemodelan data yang aman menggunakan konsep *nullable* dan *sealed class*.