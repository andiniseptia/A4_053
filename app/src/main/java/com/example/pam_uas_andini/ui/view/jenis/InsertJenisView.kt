package com.example.pam_uas_andini.ui.view.jenis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.navigation.DestinasiInsertJenis
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.InsertJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.JenisUiEvent
import com.example.pam_uas_andini.ui.viewmodel.jenis.JenisUiState
import kotlinx.coroutines.launch

@Composable
fun FormJenis(
    jenisUiEvent: JenisUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (JenisUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = jenisUiEvent.id_jenis,
            onValueChange = { onValueChange(jenisUiEvent.copy(id_jenis = it)) },
            label = { Text("ID Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = jenisUiEvent.nama_jenis,
            onValueChange = { onValueChange(jenisUiEvent.copy(nama_jenis = it)) },
            label = { Text("Nama Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = jenisUiEvent.deskripsi_jenis,
            onValueChange = { onValueChange(jenisUiEvent.copy(deskripsi_jenis = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}