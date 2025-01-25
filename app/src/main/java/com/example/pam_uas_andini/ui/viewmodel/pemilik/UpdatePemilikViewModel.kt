package com.example.pam_uas_andini.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.navigation.DestinasiUpdatePemilik
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import kotlinx.coroutines.launch

class UpdatePemilikViewModel (
    savedStateHandle: SavedStateHandle,
    private val pemilikRepository: PemilikRepository
): ViewModel() {
    var updatePemilikUiState by mutableStateOf(PemilikUiState())
        private set

    private val id_pemilik: String = checkNotNull(savedStateHandle[DestinasiUpdatePemilik.IDPEMILIK])

    init {
        viewModelScope.launch {
            val response = pemilikRepository.getPemilikById(id_pemilik)
            updatePemilikUiState = response.data.toUiStatePml()
        }
    }
    fun updateInsertPemilikState(pemilikUiEvent: PemilikUiEvent){
        updatePemilikUiState = PemilikUiState(pemilikUiEvent = pemilikUiEvent)
    }
    suspend fun updatePemilik() {
        viewModelScope.launch {
            try {
                pemilikRepository.updatePemilik(id_pemilik, updatePemilikUiState.pemilikUiEvent.toPml())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}