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

