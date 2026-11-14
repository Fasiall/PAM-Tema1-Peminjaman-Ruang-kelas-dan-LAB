package com.example.tugas_minggu2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.tugas_minggu2.data.PeminjamanDataPenyimpanan
import com.example.tugas_minggu2.model.Peminjaman
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Tiga keadaan layar sesuai modul: Memuat, Berhasil, Gagal
sealed class DetailUiState {
    object Memuat : DetailUiState()
    data class Berhasil(val peminjaman: Peminjaman) : DetailUiState()
    data class Gagal(val pesan: String) : DetailUiState()
}

// ViewModel detail: mengambil data dari tempat penyimpanan berdasarkan ID
class DetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Memuat)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun muatDetail(idPeminjaman: String) {
        _uiState.value = DetailUiState.Memuat
        val data = PeminjamanDataPenyimpanan.ambilBerdasarkanId(idPeminjaman)
        if (data != null) {
            _uiState.value = DetailUiState.Berhasil(data)
        } else {
            _uiState.value = DetailUiState.Gagal("Peminjaman dengan ID '$idPeminjaman' tidak ditemukan.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeminjamanDetailScreen(
    idPeminjaman: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = remember { DetailViewModel() }
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(idPeminjaman) {
        viewModel.muatDetail(idPeminjaman)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Peminjaman") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val currentState = state) {
                is DetailUiState.Memuat -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is DetailUiState.Gagal -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentState.pesan,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onBackClick) {
                            Text("Kembali ke Daftar")
                        }
                    }
                }
                is DetailUiState.Berhasil -> {
                    DetailKonten(
                        peminjaman = currentState.peminjaman,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailKonten(
    peminjaman: Peminjaman,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Kartu 1: Status & Judul Ruang
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = peminjaman.idPeminjaman,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatusLabel(status = peminjaman.status)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = peminjaman.ruangLab.namaRuangan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Jenis: ${peminjaman.ruangLab.jenis}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Kartu 2: Detail Peminjam
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Informasi Peminjam",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                BarisInformasi(label = "Nama", nilai = peminjaman.namaPeminjam)
                BarisInformasi(label = "NIM", nilai = peminjaman.nimPeminjam)
                BarisInformasi(label = "Tanggal Pinjam", nilai = peminjaman.tanggalPeminjaman)
            }
        }

        // Kartu 3: Informasi Fasilitas Ruangan
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Detail Fasilitas & Ruangan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                BarisInformasi(label = "Kode Ruang", nilai = peminjaman.ruangLab.idRuang)
                BarisInformasi(label = "Kapasitas", nilai = "${peminjaman.ruangLab.kapasitas} Orang")
                BarisInformasi(
                    label = "Fasilitas",
                    nilai = peminjaman.ruangLab.fasilitasDeskripsi ?: "Tidak ada catatan fasilitas khusus"
                )
            }
        }

        // Kartu 4: Catatan Admin (jika ada)
        if (!peminjaman.catatanAdmin.isNullOrEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Catatan Admin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = peminjaman.catatanAdmin,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun BarisInformasi(label: String, nilai: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = nilai,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
