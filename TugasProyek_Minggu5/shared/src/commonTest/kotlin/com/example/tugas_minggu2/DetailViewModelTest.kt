package com.example.tugas_minggu2

import com.example.tugas_minggu2.ui.screen.DetailUiState
import com.example.tugas_minggu2.ui.screen.DetailViewModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DetailViewModelTest {

    @Test
    fun muatDetail_idPeminjamanValid_stateBerhasil() {
        val viewModel = DetailViewModel()
        viewModel.muatDetail("P001")
        val state = viewModel.uiState.value
        assertTrue(state is DetailUiState.Berhasil)
        assertEquals("P001", state.peminjaman.idPeminjaman)
    }

    @Test
    fun muatDetail_idPeminjamanTidakValid_stateGagal() {
        val viewModel = DetailViewModel()
        viewModel.muatDetail("INVALID_ID")
        val state = viewModel.uiState.value
        assertTrue(state is DetailUiState.Gagal)
    }
}
