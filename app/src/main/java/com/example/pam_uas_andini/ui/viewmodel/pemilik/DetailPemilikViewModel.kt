package com.example.pam_uas_andini.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.PemilikDetailResponse
import com.example.pam_uas_andini.navigation.DestinasiDetailPemilik
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPemilikUiState {
    object Loading : DetailPemilikUiState()
    data class Success(val pemilik: PemilikDetailResponse) : DetailPemilikUiState()
    object Error : DetailPemilikUiState()
}

class DetailPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pml: PemilikRepository
) : ViewModel() {

    var detailPemilikUiState: DetailPemilikUiState by mutableStateOf(DetailPemilikUiState.Loading)
        private set

    private val id_pemilik: String = checkNotNull(savedStateHandle[DestinasiDetailPemilik.IDPEMILIK])

    init {
        getPemilikById()
    }

    fun getPemilikById() {
        viewModelScope.launch {
            detailPemilikUiState = DetailPemilikUiState.Loading
            detailPemilikUiState = try {
                val pemilik = pml.getPemilikById(id_pemilik)
                DetailPemilikUiState.Success(pemilik)
        } catch (e:Exception) {
            DetailPemilikUiState.Error
        } catch (e:Exception){
            DetailPemilikUiState.Error
        }
        }
    }

    fun deletePemilik() {
        viewModelScope.launch {
            try {
                pml.deletePemilik(id_pemilik)
                getPemilikById()
            } catch (e: Exception) {
                detailPemilikUiState = DetailPemilikUiState.Error
            }
        }
    }

    fun refreshDetailPemilik() {
        viewModelScope.launch {
            detailPemilikUiState = DetailPemilikUiState.Loading
            detailPemilikUiState = try {
                val pemilik = pml.getPemilikById(id_pemilik)
                DetailPemilikUiState.Success(pemilik)
            } catch (e: Exception) {
                DetailPemilikUiState.Error
            }
        }
    }
}
