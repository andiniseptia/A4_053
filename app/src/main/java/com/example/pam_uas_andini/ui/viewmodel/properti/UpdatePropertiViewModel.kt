package com.example.pam_uas_andini.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.navigation.DestinasiUpdateProperti
import com.example.pam_uas_andini.repository.properti.PropertiRepository
import kotlinx.coroutines.launch

class UpdatePropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val propertiRepository: PropertiRepository
) : ViewModel() {
    var updatePropertiUiState by mutableStateOf(PropertiUiState())
        private set

    private val id_properti: String = checkNotNull(savedStateHandle[DestinasiUpdateProperti.IDPROPERTI])

    init {
        viewModelScope.launch {
            loadPropertiData()
            loadDropdownData()
        }
    }

    private suspend fun loadPropertiData() {
        val response = propertiRepository.getPropertiById(id_properti)
        updatePropertiUiState = response.data.toUiStatePrt()
    }

    private suspend fun loadDropdownData() {
        val jenisResponse = propertiRepository.getJenis()
        val pemilikResponse = propertiRepository.getPemilik()
        val manajerResponse = propertiRepository.getManajer()

        updatePropertiUiState = updatePropertiUiState.copy(
            listJenis = jenisResponse.data,
            listPemilik = pemilikResponse.data,
            listManajer = manajerResponse.data
        )
    }

    fun updateInsertPropertiState(propertiUiEvent: PropertiUiEvent) {
        updatePropertiUiState = updatePropertiUiState.copy(propertiUiEvent = propertiUiEvent)
    }

    suspend fun updateProperti() {
        viewModelScope.launch {
            try {
                propertiRepository.updateProperti(id_properti, updatePropertiUiState.propertiUiEvent.toPrt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}