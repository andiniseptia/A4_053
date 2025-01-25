package com.example.pam_uas_andini.ui.view.manajer

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
import com.example.pam_uas_andini.navigation.DestinasiInsertManajer
import com.example.pam_uas_andini.navigation.DestinasiInsertPemilik
import com.example.pam_uas_andini.ui.view.pemilik.EntryBodyPemilik
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.UpdateManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.UpdatePemilikViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.Modifier
import com.example.pam_uas_andini.navigation.DestinasiUpdateManajer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateManajerView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    viewModel: UpdateManajerViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateManajer.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){padding ->
        EntryBodyManajer(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            manajerUiState = viewModel.updateManajerUiState,
            onManajerValueChange = viewModel::updateInsertManajerState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateManajer()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}