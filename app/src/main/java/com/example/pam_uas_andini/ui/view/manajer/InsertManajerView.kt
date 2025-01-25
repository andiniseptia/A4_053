package com.example.pam_uas_andini.ui.view.manajer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.navigation.DestinasiInsertManajer
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.InsertManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.ManajerUiEvent
import com.example.pam_uas_andini.ui.viewmodel.manajer.ManajerUiState
import kotlinx.coroutines.launch

@Composable
fun EntryBodyManajer(
    manajerUiState: ManajerUiState,
    onManajerValueChange: (ManajerUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormManajer(
            manajerUiEvent = manajerUiState.manajerUiEvent,
            onValueChange = onManajerValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormManajer(
    manajerUiEvent: ManajerUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (ManajerUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = manajerUiEvent.id_manajer,
            onValueChange = { onValueChange(manajerUiEvent.copy(id_manajer = it)) },
            label = { Text("ID Manajer") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = manajerUiEvent.nama_manajer,
            onValueChange = { onValueChange(manajerUiEvent.copy(nama_manajer = it)) },
            label = { Text("Nama Manajer") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = manajerUiEvent.kontak_manajer,
            onValueChange = { onValueChange(manajerUiEvent.copy(kontak_manajer = it)) },
            label = { Text("Kontak Manajer") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}