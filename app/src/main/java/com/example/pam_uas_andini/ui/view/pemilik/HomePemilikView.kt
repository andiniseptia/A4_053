package com.example.pam_uas_andini.ui.view.pemilik

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_uas_andini.R
import com.example.pam_uas_andini.customwidget.CostumeTopAppBar
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.navigation.DestinasiHomePemilik
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.HomePemilikUiState
import com.example.pam_uas_andini.ui.viewmodel.pemilik.HomePemilikViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePemilikView(
    navigateBack: () -> Unit,
    navigateToEdit: (String) -> Unit,
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePemilik.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPemilik()
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
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pemilik", tint = Color.White)
            }
        },
    ) { innerPadding ->
        HomeStatus(
            homePemilikUiState = viewModel.pemilikUIState,
            retryAction = { viewModel.getPemilik() }, modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick, onDeleteClick = {
                viewModel.deletePemilik(it.id_pemilik)
                viewModel.getPemilik()
            },
            onEditClick = { navigateToEdit(it.id_pemilik) }
        )
    }
}

@Composable
fun HomeStatus(
    homePemilikUiState: HomePemilikUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemilik) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onEditClick: (Pemilik) -> Unit = {}
) {
    when(homePemilikUiState) {
        is HomePemilikUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomePemilikUiState.Success ->
            if (homePemilikUiState.pemilik.isEmpty()) {
                return Box (modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data pemilik")
                }
            } else {
                PmlLayout(
                    pemilik = homePemilikUiState.pemilik, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_pemilik)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onEditClick = { onEditClick(it) }
                )
            }
        is HomePemilikUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun PmlLayout(
    pemilik: List<Pemilik>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pemilik) -> Unit,
    onDeleteClick: (Pemilik) -> Unit = {},
    onEditClick: (Pemilik) -> Unit = {}
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pemilik) { pemilik ->
            PmlCard(
                pemilik = pemilik,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pemilik) },
                onDeleteClick = {
                    onDeleteClick(pemilik)
                },
                onEditClick = { onEditClick(pemilik) }
            )
        }
    }
}

@Composable
fun PmlCard(
    pemilik: Pemilik,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemilik) -> Unit = {},
    onEditClick: (Pemilik) -> Unit = {}
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
                Icon(imageVector = Icons.Filled.Person, contentDescription = "nama", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = pemilik.nama_pemilik,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )

                IconButton(onClick = { onEditClick(pemilik) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                IconButton(onClick = { onDeleteClick(pemilik) }) {
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
                    text = pemilik.id_pemilik,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

            }
        }
    }
}