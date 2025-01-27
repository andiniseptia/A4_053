package com.example.pam_uas_andini.ui.view.pemilik

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
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
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.navigation.DestinasiDetailPemilik
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.DetailPemilikUiState
import com.example.pam_uas_andini.ui.viewmodel.pemilik.DetailPemilikViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPemilikView(
    onBack: () -> Unit = { },
    onEditClick: () -> Unit = { },
    onDeleteClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailPemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(Unit) {
        viewModel.refreshDetailPemilik() // Refresh detail saat halaman ini diluncurkan
    }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPemilik.titleRes,
                canNavigateBack = true,
                //scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = colorResource(id = R.color.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        DetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailPemilikUiState = viewModel.detailPemilikUiState,
            onDeleteClick = {
                viewModel.deletePemilik()
                onBack()
            }
        )
    }
}

@Composable
fun DetailStatus(
    modifier: Modifier = Modifier,
    detailPemilikUiState: DetailPemilikUiState,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    when (detailPemilikUiState) {
        is DetailPemilikUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailPemilikUiState.Success -> {
            // Access the Pemilik object from the data property
            val pemilik = detailPemilikUiState.pemilik.data
            Column(modifier = modifier.padding(16.dp)) {
                ItemDetailPemilik(pemilik = pemilik)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { deleteConfirmationRequired = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary)
                    ),
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
        is DetailPemilikUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading data")
            }
        }
    }
}

@Composable
fun ItemDetailPemilik(
    modifier: Modifier = Modifier,
    pemilik: Pemilik
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
            ComponentDetailPemilik(judul = "ID Pemilik", isinya = pemilik.id_pemilik)
            ComponentDetailPemilik(judul = "Nama Pemilik", isinya = pemilik.nama_pemilik)
            ComponentDetailPemilik(judul = "Kontak Pemilik", isinya = pemilik.kontak_pemilik)
        }
    }
}

@Composable
fun ComponentDetailPemilik(
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
            color = colorResource(id = R.color.primary)
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