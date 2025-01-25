package com.example.pam_uas_andini.ui.viewmodel.pemilik

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePemilikUiState {
    data class Success(val pemilik: List<Pemilik>) : HomePemilikUiState()
    object Error: HomePemilikUiState()
    object Loading: HomePemilikUiState()
}

class HomePemilikViewModel(private val pml: PemilikRepository) : ViewModel() {
    var pemilikUIState: HomePemilikUiState by mutableStateOf(HomePemilikUiState.Loading)
        private set

    init {
        getPemilik()
    }

    fun getPemilik() {
        viewModelScope.launch {
            pemilikUIState = HomePemilikUiState.Loading
            pemilikUIState = try {
                HomePemilikUiState.Success(pml.getPemilik().data)
            } catch (e: IOException) {
                pemilikUIState = HomePemilikUiState.Error
                HomePemilikUiState.Error
            } catch (e: HttpException) {
                pemilikUIState = HomePemilikUiState.Error
                HomePemilikUiState.Error
            }
        }
    }

    fun deletePemilik(id_pemilik: String) {
        viewModelScope.launch {
            try {
                pml.deletePemilik(id_pemilik)
            } catch (e: IOException) {
                HomePemilikUiState.Error
            } catch (e: HttpException) {
                HomePemilikUiState.Error
            }
        }
    }
}