package com.example.pam_uas_andini.ui.view.jenis

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.navigation.DestinasiHomeJenis
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.HomeJenisUiState
import com.example.pam_uas_andini.ui.viewmodel.jenis.HomeJenisViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisView(
    navigateBack: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToEdit: (String) -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeJenis.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getJenis()
                },
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = colorResource(id = R.color.primary)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Jenis", tint = Color.White)
            }
        },
    ) { innerPadding ->
        HomeStatus(
            homeJenisUiState = viewModel.jenisUIState,
            retryAction = { viewModel.getJenis() }, modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick, onDeleteClick = {
                viewModel.deleteJenis(it.id_jenis)
                viewModel.getJenis()
            },
            onEditClick = { navigateToEdit(it.id_jenis) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisFilteredView(
    id_jenis: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    navigateBack: () -> Unit,
    navController: NavController,
    viewModel: HomeJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    LaunchedEffect(id_jenis) {
        viewModel.getJenisById(id_jenis) // Ambil jenis berdasarkan ID
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeJenis.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        when (val state = viewModel.jenisUIState) {
            is HomeJenisUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is HomeJenisUiState.Success -> {
                // Gunakan JnsLayout untuk menampilkan jenis dalam bentuk kartu
                JnsLayout(
                    jenis = state.jenis,
                    modifier = Modifier.padding(innerPadding),
                    onDetailClick = { navigateToDetail(it.id_jenis) },
                    onDeleteClick = { /* Implementasi penghapusan jika diperlukan */ }
                )
            }
            is HomeJenisUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error loading data")
                }
            }
        }
    }
}

@Composable
fun HomeStatus(
    homeJenisUiState: HomeJenisUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Jenis) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onEditClick: (Jenis) -> Unit = {}
) {
    when(homeJenisUiState) {
        is HomeJenisUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeJenisUiState.Success ->
            if (homeJenisUiState.jenis.isEmpty()) {
                return Box (modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data jenis")
                }
            } else {
                JnsLayout(
                    jenis = homeJenisUiState.jenis, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_jenis)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onEditClick = { onEditClick(it) }
                )
            }
        is HomeJenisUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column (
        modifier =  modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun JnsLayout(
    jenis: List<Jenis>,
    modifier: Modifier = Modifier,
    onDetailClick: (Jenis) -> Unit,
    onDeleteClick: (Jenis) -> Unit = {},
    onEditClick: (Jenis) -> Unit = {}
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(jenis) { jenis ->
            JnsCard(
                jenis = jenis,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(jenis) },
                onDeleteClick = {
                    onDeleteClick(jenis)
                },
                onEditClick = { onEditClick(jenis) }
            )
        }
    }
}

@Composable
fun JnsCard(
    jenis: Jenis,
    modifier: Modifier = Modifier,
    onDeleteClick: (Jenis) -> Unit = {},
    onEditClick: (Jenis) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.cardhome)
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "nama", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jenis.nama_jenis,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )

                IconButton(onClick = { onEditClick(jenis) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { onDeleteClick(jenis) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Row() {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "nama", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jenis.id_jenis,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}