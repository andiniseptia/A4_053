package com.example.pam_uas_andini.ui.view.jenis

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.navigation.DestinasiInsertJenis
import com.example.pam_uas_andini.navigation.DestinasiInsertPemilik
import com.example.pam_uas_andini.ui.view.pemilik.EntryBodyPemilik
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.UpdateJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.UpdatePemilikViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.Modifier
import com.example.pam_uas_andini.navigation.DestinasiUpdateJenis

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateJenisView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    viewModel: UpdateJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateJenis.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){padding ->
        EntryBodyJenis(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            jenisUiState = viewModel.updateJenisUiState,
            onJenisValueChange = viewModel::updateInsertJenisState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateJenis()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}