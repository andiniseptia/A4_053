package com.example.pam_uas_andini.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.pam_uas_andini.ui.viewmodel.HomeNavigation
import com.example.pam_uas_andini.ui.viewmodel.HomeViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.TopAppBarHome
import com.example.pam_uas_andini.navigation.DestinasiHomeJenis
import com.example.pam_uas_andini.navigation.DestinasiHomeManajer
import com.example.pam_uas_andini.navigation.DestinasiHomePemilik
import com.example.pam_uas_andini.navigation.DestinasiHomeProperti

@Composable
fun HomeView(
    navigateToPemilik: () -> Unit,
    navigateToManajer: () -> Unit,
    navigateToJenis: () -> Unit,
    navigateToProperti: () -> Unit,
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBarHome()
        },
        modifier = modifier
    ) { innerPadding -> // This is the content parameter
        // Use innerPadding to ensure the content is laid out correctly
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // Apply padding to avoid overlap with the top bar
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        LaunchedEffect(uiState.navigateToProperti) {
            if (uiState.navigateToProperti) {
                navController.navigate(DestinasiHomeProperti.route)
                viewModel.resetNavigation()
            }
        }
        LaunchedEffect(uiState.navigateToPemilik) {
            if (uiState.navigateToPemilik) {
                navController.navigate(DestinasiHomePemilik.route)
                viewModel.resetNavigation()
            }
        }
        LaunchedEffect(uiState.navigateToManajer) {
            if (uiState.navigateToManajer) {
                navController.navigate(DestinasiHomeManajer.route)
                viewModel.resetNavigation()
            }
        }
        LaunchedEffect(uiState.navigateToJenis) {
            if (uiState.navigateToJenis) {
                navController.navigate(DestinasiHomeJenis.route)
                viewModel.resetNavigation()
            }
        }
        }

    }
}