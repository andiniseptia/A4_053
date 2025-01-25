package com.example.pam_uas_andini.ui.viewmodel.manajer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.repository.manajer.ManajerRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeManajerUiState {
    data class Success(val manajer: List<Manajer>) : HomeManajerUiState()
    object Error: HomeManajerUiState()
    object Loading: HomeManajerUiState()
}

class HomeManajerViewModel(private val mnj: ManajerRepository) : ViewModel() {
    var manajerUIState: HomeManajerUiState by mutableStateOf(HomeManajerUiState.Loading)
        private set

    init {
        getManajer()
    }

    fun getManajer() {
        viewModelScope.launch {
            manajerUIState = HomeManajerUiState.Loading
            manajerUIState = try {
                HomeManajerUiState.Success(mnj.getManajer().data)
            } catch (e: IOException) {
                manajerUIState = HomeManajerUiState.Error
                HomeManajerUiState.Error
            } catch (e: HttpException) {
                manajerUIState = HomeManajerUiState.Error
                HomeManajerUiState.Error
            }
        }
    }

    fun deleteManajer(id_manajer: String) {
        viewModelScope.launch {
            try {
                mnj.deleteManajer(id_manajer)
            } catch (e: IOException) {
                HomeManajerUiState.Error
            } catch (e: HttpException) {
                HomeManajerUiState.Error
            }
        }
    }
}