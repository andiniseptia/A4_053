package com.example.pam_uas_andini.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.repository.manajer.ManajerRepository
import kotlinx.coroutines.launch


fun ManajerUiEvent.toMnj(): Manajer = Manajer(
    id_manajer = id_manajer,
    nama_manajer = nama_manajer,
    kontak_manajer = kontak_manajer
)

fun Manajer.toUiStateMnj(): ManajerUiState = ManajerUiState(
    manajerUiEvent = toManajerUiEvent()
)

fun Manajer.toManajerUiEvent(): ManajerUiEvent = ManajerUiEvent(
    id_manajer = id_manajer,
    nama_manajer = nama_manajer,
    kontak_manajer = kontak_manajer
)

data class ManajerUiState(
    val manajerUiEvent: ManajerUiEvent = ManajerUiEvent()
)

data class ManajerUiEvent(
    val id_manajer: String = "",
    val nama_manajer: String = "",
    val kontak_manajer: String = ""
)