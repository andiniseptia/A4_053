package com.example.pam_uas_andini.ui.view.properti

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.navigation.DestinasiDetailProperti
import com.example.pam_uas_andini.navigation.DestinasiHomeJenisFiltered
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.DetailPropertiUiState
import com.example.pam_uas_andini.ui.viewmodel.properti.DetailPropertiViewModel

@Composable
fun DetailStatus(
    onJenisClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    detailPropertiUiState: DetailPropertiUiState,
    onDeleteClick: () -> Unit
) {
    val navController = rememberNavController()
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    when (detailPropertiUiState) {
        is DetailPropertiUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailPropertiUiState.Success -> {
            // Access the Pemilik object from the data property
            val properti = detailPropertiUiState.properti.data
            Column(modifier = modifier.padding(16.dp)) {
                ItemDetailProperti(properti = properti)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onJenisClick(properti.id_jenis) }, // Panggil fungsi onJenisClick dengan ID jenis
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary)
                    )
                ) {
                    Text(text = "Lihat Jenis")
                }

                Button(
                    onClick = { deleteConfirmationRequired = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary)
                    )
                ) {
                    Text(text = "Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false }
                    )
                }
            }
        }
        is DetailPropertiUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading data")
            }
        }
    }
}

@Composable
fun ItemDetailProperti(
    modifier: Modifier = Modifier,
    properti: Properti
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.carddtl)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailProperti(judul = "ID Properti", isinya = properti.id_properti)
            ComponentDetailProperti(judul = "Nama Properti", isinya = properti.nama_properti)
            ComponentDetailProperti(judul = "Deskripsi Properti", isinya = properti.deskripsi_properti)
            ComponentDetailProperti(judul = "Status Properti", isinya = properti.status_properti)
            ComponentDetailProperti(judul = "Harga Properti", isinya = properti.harga)
            ComponentDetailProperti(judul = "Lokasi Properti", isinya = properti.lokasi)
            ComponentDetailProperti(judul = "ID Manajer", isinya = properti.id_manajer)
            ComponentDetailProperti(judul = "ID Pemilik", isinya = properti.id_pemilik)
            ComponentDetailProperti(judul = "ID Jenis", isinya = properti.id_jenis)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ComponentDetailProperti(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.primary),
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}