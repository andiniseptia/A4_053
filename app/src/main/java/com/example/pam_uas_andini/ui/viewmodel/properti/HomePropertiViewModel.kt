package com.example.pam_uas_andini.ui.viewmodel.properti


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import com.example.pam_uas_andini.repository.properti.PropertiRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomePropertiUiState {
    data class Success(val properti: List<Properti>) : HomePropertiUiState()
    object Error: HomePropertiUiState()
    object Loading: HomePropertiUiState()
}

class HomePropertiViewModel(private val prt: PropertiRepository) : ViewModel() {
    var propertiUIState: HomePropertiUiState by mutableStateOf(HomePropertiUiState.Loading)
        private set

    init {
        getProperti()
    }

    fun getProperti() {
        viewModelScope.launch {
            propertiUIState = HomePropertiUiState.Loading
            propertiUIState = try {
                HomePropertiUiState.Success(prt.getProperti().data)
            } catch (e: java.io.IOException) {
                propertiUIState = HomePropertiUiState.Error
                HomePropertiUiState.Error
            } catch (e: HttpException) {
                propertiUIState = HomePropertiUiState.Error
                HomePropertiUiState.Error
            }
        }
    }

    fun deleteProperti(id_properti: String) {
        viewModelScope.launch {
            try {
                prt.deleteProperti(id_properti)
            } catch (e: java.io.IOException) {
                HomePropertiUiState.Error
            } catch (e: HttpException) {
                HomePropertiUiState.Error
            }
        }
    }
}