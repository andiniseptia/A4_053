package com.example.pam_uas_andini.ui.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.lang.reflect.Modifier

sealed class HomeNavigation {
    object HomeProperti : HomeNavigation()
    object HomePemilik : HomeNavigation()
    object HomeManajer : HomeNavigation()
    object HomeJenis : HomeNavigation()
}

data class HomeUiState(
    val navigateToProperti: Boolean = false,
    val navigateToPemilik: Boolean = false,
    val navigateToManajer: Boolean = false,
    val navigateToJenis: Boolean = false
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun navigateTo(destination: HomeNavigation) {
        _uiState.update {
            when (destination) {
                HomeNavigation.HomeProperti -> it.copy(navigateToProperti = true)
                HomeNavigation.HomePemilik -> it.copy(navigateToPemilik = true)
                HomeNavigation.HomeManajer -> it.copy(navigateToManajer = true)
                HomeNavigation.HomeJenis -> it.copy(navigateToJenis = true)
            }
        }
    }

    fun resetNavigation() {
        _uiState.update { HomeUiState() }
    }
}