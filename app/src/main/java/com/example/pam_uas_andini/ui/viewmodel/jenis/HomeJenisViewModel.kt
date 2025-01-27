package com.example.pam_uas_andini.ui.viewmodel.jenis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.repository.jenis.JenisRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeJenisUiState {
    data class Success(val jenis: List<Jenis>) : HomeJenisUiState()
    object Error: HomeJenisUiState()
    object Loading: HomeJenisUiState()
}

class HomeJenisViewModel(private val jns: JenisRepository) : ViewModel() {
    var jenisUIState: HomeJenisUiState by mutableStateOf(HomeJenisUiState.Loading)
        private set

    init {
        getJenis()
    }

    fun getJenis() {
        viewModelScope.launch {
            jenisUIState = HomeJenisUiState.Loading
            jenisUIState = try {
                HomeJenisUiState.Success(jns.getJenis().data)
            } catch (e: IOException) {
                jenisUIState = HomeJenisUiState.Error
                HomeJenisUiState.Error
            } catch (e: HttpException) {
                jenisUIState = HomeJenisUiState.Error
                HomeJenisUiState.Error
            }
        }
    }

    fun getJenisById(id_jenis: String) {
        viewModelScope.launch {
            jenisUIState = HomeJenisUiState.Loading
            jenisUIState = try {
                val jenisResponse = jns.getJenisById(id_jenis)
                HomeJenisUiState.Success(listOf(jenisResponse.data)) // Mengembalikan daftar dengan satu jenis
            } catch (e: Exception) {
                HomeJenisUiState.Error
            }
        }
    }

    fun deleteJenis(id_jenis: String) {
        viewModelScope.launch {
            try {
                jns.deleteJenis(id_jenis)
            } catch (e: IOException) {
                HomeJenisUiState.Error
            } catch (e: HttpException) {
                HomeJenisUiState.Error
            }
        }
    }
}