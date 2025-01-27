package com.example.pam_uas_andini.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.JenisDetailResponse
import com.example.pam_uas_andini.navigation.DestinasiDetailJenis
import com.example.pam_uas_andini.repository.jenis.JenisRepository
import kotlinx.coroutines.launch

sealed class DetailJenisUiState {
    object Loading : DetailJenisUiState()
    data class Success(val jenis: JenisDetailResponse) : DetailJenisUiState()
    object Error : DetailJenisUiState()
}

class DetailJenisViewModel(
    savedStateHandle: SavedStateHandle,
    private val jns: JenisRepository
) : ViewModel() {

    var detailJenisUiState: DetailJenisUiState by mutableStateOf(DetailJenisUiState.Loading)
        private set

    private val id_jenis: String = checkNotNull(savedStateHandle[DestinasiDetailJenis.IDJENIS])

    fun refreshDetailJenis() {
        viewModelScope.launch {
            detailJenisUiState = DetailJenisUiState.Loading
            detailJenisUiState = try {
                val jenis = jns.getJenisById(id_jenis)
                DetailJenisUiState.Success(jenis)
            } catch (e: Exception) {
                DetailJenisUiState.Error
            }
        }
    }
}
