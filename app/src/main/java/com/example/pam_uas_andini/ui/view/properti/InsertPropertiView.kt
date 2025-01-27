package com.example.pam_uas_andini.ui.view.properti

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.navigation.DestinasiInsertProperti
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.InsertPropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.PropertiUiEvent
import com.example.pam_uas_andini.ui.viewmodel.properti.PropertiUiState
import kotlinx.coroutines.launch


@Composable
fun FormProperti(
    propertiUiEvent: PropertiUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PropertiUiEvent) -> Unit = {},
    enabled: Boolean = true,
    listJenis: List<Jenis>,
    listPemilik: List<Pemilik>,
    listManajer: List<Manajer>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = propertiUiEvent.id_properti,
            onValueChange = { onValueChange(propertiUiEvent.copy(id_properti = it)) },
            label = { Text("ID Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = propertiUiEvent.nama_properti,
            onValueChange = { onValueChange(propertiUiEvent.copy(nama_properti = it)) },
            label = { Text("Nama Properti") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = propertiUiEvent.deskripsi_properti,
            onValueChange = { onValueChange(propertiUiEvent.copy(deskripsi_properti = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = propertiUiEvent.lokasi,
            onValueChange = { onValueChange(propertiUiEvent.copy(lokasi = it)) },
            label = { Text("Lokasi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = propertiUiEvent.harga,
            onValueChange = { onValueChange(propertiUiEvent.copy(harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Status Radio Group
        Text(
            text = "Status",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp) // Tambahkan padding jika perlu
        )

        StatusRadioGroup(
            selectedStatus = propertiUiEvent.status_properti,
            onStatusSelected = { selectedStatus ->
                onValueChange(propertiUiEvent.copy(status_properti = selectedStatus))
            }
        )

        DropdownJenis(
            selectedJenis = propertiUiEvent.id_jenis,
            onJenisSelected = { selectedJenis ->
                onValueChange(propertiUiEvent.copy(id_jenis = selectedJenis))
            },
            listJenis = listJenis
        )

        DropdownPemilik(
            selectedPemilik = propertiUiEvent.id_pemilik,
            onPemilikSelected = { selectedPemilik ->
                onValueChange(propertiUiEvent.copy(id_pemilik = selectedPemilik))
            },
            listPemilik = listPemilik
        )

        DropdownManajer(
            selectedManajer = propertiUiEvent.id_manajer,
            onManajerSelected = { selectedManajer ->
                onValueChange(propertiUiEvent.copy(id_manajer = selectedManajer))
            },
            listManajer = listManajer
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownJenis(
    selectedJenis: String,
    onJenisSelected: (String) -> Unit,
    listJenis: List<Jenis>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedJenis,
            onValueChange = { },
            label = { Text("Pilih Jenis Properti") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listJenis.forEach { jenis ->
                DropdownMenuItem(
                    onClick = {
                        onJenisSelected(jenis.id_jenis) // Memperbarui state di ViewModel
                        expanded = false
                    },
                    text = { Text(jenis.nama_jenis ?: "Jenis Tidak Diketahui") }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownPemilik(
    selectedPemilik: String,
    onPemilikSelected: (String) -> Unit,
    listPemilik: List<Pemilik>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedPemilik,
            onValueChange = { /* Tidak diperlukan untuk dropdown readonly */ },
            label = { Text("Pilih Pemilik Properti") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listPemilik.forEach { pemilik ->
                DropdownMenuItem(
                    onClick = {
                        onPemilikSelected(pemilik.id_pemilik) // Memperbarui state di ViewModel
                        expanded = false
                    },
                    text = { Text(pemilik.nama_pemilik ?: "Jenis Tidak Diketahui") }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownManajer(
    selectedManajer: String,
    onManajerSelected: (String) -> Unit,
    listManajer: List<Manajer>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedManajer,
            onValueChange = { /* Tidak diperlukan untuk dropdown readonly */ },
            label = { Text("Pilih Manajer Properti") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listManajer.forEach { manajer ->
                DropdownMenuItem(
                    onClick = {
                        onManajerSelected(manajer.id_manajer) // Memperbarui state di ViewModel
                        expanded = false
                    },
                    text = { Text(manajer.nama_manajer ?: "Manajer Tidak Diketahui") }
                )
            }
        }
    }
}

@Composable
fun StatusRadioGroup(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    val statuses = listOf("Tersedia", "Tersewa", "Dijual")

    Column {
        statuses.forEach { status ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStatusSelected(status) }
            ) {
                RadioButton(
                    selected = (status == selectedStatus),
                    onClick = { onStatusSelected(status) }
                )
                Text(text = status, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
