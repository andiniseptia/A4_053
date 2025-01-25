package com.example.pam_uas_andini.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.navigation.DestinasiUpdateManajer
import com.example.pam_uas_andini.repository.manajer.ManajerRepository
import kotlinx.coroutines.launch

class UpdateManajerViewModel (
    savedStateHandle: SavedStateHandle,
    private val manajerRepository: ManajerRepository
): ViewModel() {
    var updateManajerUiState by mutableStateOf(ManajerUiState())
        private set

    private val id_manajer: String = checkNotNull(savedStateHandle[DestinasiUpdateManajer.IDMANAJER])

    init {
        viewModelScope.launch {
            val response = manajerRepository.getManajerById(id_manajer)
            updateManajerUiState = response.data.toUiStateMnj()
        }
    }
    fun updateInsertManajerState(manajerUiEvent: ManajerUiEvent){
        updateManajerUiState = ManajerUiState(manajerUiEvent = manajerUiEvent)
    }
    suspend fun updateManajer() {
        viewModelScope.launch {
            try {
                manajerRepository.updateManajer(id_manajer, updateManajerUiState.manajerUiEvent.toMnj())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}