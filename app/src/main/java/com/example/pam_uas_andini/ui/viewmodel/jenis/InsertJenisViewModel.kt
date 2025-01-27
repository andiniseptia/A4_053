package com.example.pam_uas_andini.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.repository.jenis.JenisRepository
import kotlinx.coroutines.launch



data class JenisUiState(
    val jenisUiEvent: JenisUiEvent = JenisUiEvent()
)

data class JenisUiEvent(
    val id_jenis: String = "",
    val nama_jenis: String = "",
    val deskripsi_jenis: String = ""
)