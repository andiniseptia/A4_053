package com.example.pam_uas_andini.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.ManajerDetailResponse
import com.example.pam_uas_andini.navigation.DestinasiDetailManajer
import com.example.pam_uas_andini.repository.manajer.ManajerRepository
import kotlinx.coroutines.launch

sealed class DetailManajerUiState {
    object Loading : DetailManajerUiState()
    data class Success(val manajer: ManajerDetailResponse) : DetailManajerUiState()
    object Error : DetailManajerUiState()
}

class DetailManajerViewModel(
    savedStateHandle: SavedStateHandle,
    private val mnj: ManajerRepository
) : ViewModel() {

    var detailManajerUiState: DetailManajerUiState by mutableStateOf(DetailManajerUiState.Loading)
        private set

    private val id_manajer: String = checkNotNull(savedStateHandle[DestinasiDetailManajer.IDMANAJER])

    fun refreshDetailManajer() {
        viewModelScope.launch {
            detailManajerUiState = DetailManajerUiState.Loading
            detailManajerUiState = try {
                val manajer = mnj.getManajerById(id_manajer)
                DetailManajerUiState.Success(manajer)
            } catch (e: Exception) {
                DetailManajerUiState.Error
            }
        }
    }
}
