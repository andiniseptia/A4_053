package com.example.pam_uas_andini.ui.viewmodel.properti

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.PemilikDetailResponse
import com.example.pam_uas_andini.model.PropertiDetailResponse
import com.example.pam_uas_andini.navigation.DestinasiDetailPemilik
import com.example.pam_uas_andini.navigation.DestinasiDetailProperti
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import com.example.pam_uas_andini.repository.properti.PropertiRepository
import kotlinx.coroutines.launch

sealed class DetailPropertiUiState {
    object Loading : DetailPropertiUiState()
    data class Success(val properti: PropertiDetailResponse) : DetailPropertiUiState()
    object Error : DetailPropertiUiState()
}

class DetailPropertiViewModel(
    savedStateHandle: SavedStateHandle,
    private val prt: PropertiRepository
) : ViewModel() {

    var detailPropertiUiState: DetailPropertiUiState by mutableStateOf(DetailPropertiUiState.Loading)
        private set

    private val id_properti: String = checkNotNull(savedStateHandle[DestinasiDetailProperti.IDPROPERTI])

    fun refreshDetailProperti() {
        viewModelScope.launch {
            detailPropertiUiState = DetailPropertiUiState.Loading
            detailPropertiUiState = try {
                val properti = prt.getPropertiById(id_properti)
                DetailPropertiUiState.Success(properti)
            } catch (e: Exception) {
                DetailPropertiUiState.Error
            }
        }
    }
}
