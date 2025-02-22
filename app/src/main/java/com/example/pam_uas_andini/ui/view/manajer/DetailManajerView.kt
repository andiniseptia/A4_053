package com.example.pam_uas_andini.ui.view.manajer

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
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.navigation.DestinasiDetailManajer
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.DetailManajerUiState
import com.example.pam_uas_andini.ui.viewmodel.manajer.DetailManajerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailManajerView(
    onBack: () -> Unit = { },
    onEditClick: () -> Unit = { },
    onDeleteClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailManajerViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(Unit) {
        viewModel.refreshDetailManajer() // Refresh detail saat halaman ini diluncurkan
    }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailManajer.titleRes,
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
            detailManajerUiState = viewModel.detailManajerUiState,
            onDeleteClick = {
                viewModel.deleteManajer()
                onBack()
            }
        )
    }
}

@Composable
fun DetailStatus(
    modifier: Modifier = Modifier,
    detailManajerUiState: DetailManajerUiState,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    when (detailManajerUiState) {
        is DetailManajerUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailManajerUiState.Success -> {
            // Access the Pemilik object from the data property
            val manajer = detailManajerUiState.manajer.data
            Column(modifier = modifier.padding(16.dp)) {
                ItemDetailManajer(manajer = manajer)

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
        is DetailManajerUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading data")
            }
        }
    }
}

@Composable
fun ItemDetailManajer(
    modifier: Modifier = Modifier,
    manajer: Manajer
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
            ComponentDetailManajer(judul = "ID Manajer", isinya = manajer.id_manajer)
            ComponentDetailManajer(judul = "Nama Manajer", isinya = manajer.nama_manajer)
            ComponentDetailManajer(judul = "Kontak Manajer", isinya = manajer.kontak_manajer)
        }
    }
}

@Composable
fun ComponentDetailManajer(
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
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            )
        Text(
            text = isinya,
            fontSize = 20.sp,
            color = colorResource(id = R.color.primary),
            fontWeight = FontWeight.Bold
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