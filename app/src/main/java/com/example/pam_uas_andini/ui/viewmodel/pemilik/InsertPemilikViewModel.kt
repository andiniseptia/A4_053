package com.example.pam_uas_andini.ui.viewmodel.pemilik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import kotlinx.coroutines.launch

data class PemilikUiState(
    val pemilikUiEvent: PemilikUiEvent = PemilikUiEvent()
)

data class PemilikUiEvent(
    val id_pemilik: String = "",
    val nama_pemilik: String = "",
    val kontak_pemilik: String = ""
)