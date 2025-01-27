package com.example.pam_uas_andini.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.repository.jenis.JenisRepository

sealed class HomeJenisUiState {
    data class Success(val jenis: List<Jenis>) : HomeJenisUiState()
    object Error: HomeJenisUiState()
    object Loading: HomeJenisUiState()
}

class HomeJenisViewModel(private val jns: JenisRepository) : ViewModel() {
    var jenisUIState: HomeJenisUiState by mutableStateOf(HomeJenisUiState.Loading)
        private set

}