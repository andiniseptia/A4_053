package com.example.pam_uas_andini.ui.viewmodel.properti

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.repository.properti.PropertiRepository
import kotlinx.coroutines.launch


fun PropertiUiEvent.toPrt(): Properti = Properti(
    id_properti = id_properti,
    nama_properti = nama_properti,
    deskripsi_properti = deskripsi_properti,
    lokasi = lokasi,
    harga = harga,
    status_properti = status_properti,
    id_jenis = id_jenis,
    id_pemilik = id_pemilik,
    id_manajer = id_manajer
)

fun Properti.toUiStatePrt(): PropertiUiState = PropertiUiState(
    propertiUiEvent = toPropertiUiEvent()
)

fun Properti.toPropertiUiEvent(): PropertiUiEvent = PropertiUiEvent (
    id_properti = id_properti,
    nama_properti = nama_properti,
    deskripsi_properti = deskripsi_properti,
    lokasi = lokasi,
    harga = harga,
    status_properti = status_properti,
    id_jenis = id_jenis,
    id_pemilik = id_pemilik,
    id_manajer = id_manajer
)

data class PropertiUiState(
    val propertiUiEvent: PropertiUiEvent = PropertiUiEvent(),
    val listJenis: List<Jenis> = emptyList(),
    val listPemilik: List<Pemilik> = emptyList(),
    val listManajer: List<Manajer> = emptyList()
)

data class PropertiUiEvent(
    val id_properti: String = "",
    val nama_properti: String = "",
    val deskripsi_properti: String = "",
    val lokasi: String = "",
    val harga: String = "",
    val status_properti: String = "",
    val id_jenis: String = "",
    val id_pemilik: String = "",
    val id_manajer: String = ""
)