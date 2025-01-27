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

}