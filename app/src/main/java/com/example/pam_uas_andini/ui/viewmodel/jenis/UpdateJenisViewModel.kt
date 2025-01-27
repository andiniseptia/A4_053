package com.example.pam_uas_andini.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.navigation.DestinasiUpdateJenis
import com.example.pam_uas_andini.repository.jenis.JenisRepository
import kotlinx.coroutines.launch

class UpdateJenisViewModel (
    savedStateHandle: SavedStateHandle,
    private val jenisRepository: JenisRepository
): ViewModel() {
    var updateJenisUiState by mutableStateOf(JenisUiState())
        private set

    private val id_jenis: String = checkNotNull(savedStateHandle[DestinasiUpdateJenis.IDJENIS])

    init {
        viewModelScope.launch {
            val response = jenisRepository.getJenisById(id_jenis)
            updateJenisUiState = response.data.toUiStateJns()
        }
    }
    fun updateInsertJenisState(jenisUiEvent: JenisUiEvent){
        updateJenisUiState = JenisUiState(jenisUiEvent = jenisUiEvent)
    }
    suspend fun updateJenis() {
        viewModelScope.launch {
            try {
                jenisRepository.updateJenis(id_jenis, updateJenisUiState.jenisUiEvent.toJns())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}