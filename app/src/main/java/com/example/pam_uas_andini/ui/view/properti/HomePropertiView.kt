package com.example.pam_uas_andini.ui.view.properti

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.navigation.DestinasiHomeProperti
import com.example.pam_uas_andini.ui.viewmodel.PenyediaViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.HomePropertiUiState
import com.example.pam_uas_andini.ui.viewmodel.properti.HomePropertiViewModel


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
fun PrtLayout(
    properti: List<Properti>,
    modifier: Modifier = Modifier,
    onDetailClick: (Properti) -> Unit,
    onDeleteClick: (Properti) -> Unit = {},
    onEditClick: (Properti) -> Unit = {}

) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(properti) { properti ->
            PrtCard(
                properti = properti,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(properti) },
                onDeleteClick = {
                    onDeleteClick(properti)
                },
                onEditClick = { onEditClick(properti) }
            )
        }
    }
}

@Composable
fun PrtCard(
    properti: Properti,
    modifier: Modifier = Modifier,
    onDeleteClick: (Properti) -> Unit = {},
    onEditClick: (Properti) -> Unit = {}
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
                Icon(imageVector = Icons.Filled.Home, contentDescription = "nama", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = properti.nama_properti,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )

                IconButton(onClick = { onEditClick(properti) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { onDeleteClick(properti) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Column {
                Row {
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "nama", tint = Color.White)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = properti.lokasi,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Row {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "nama", tint = Color.White)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = properti.harga,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Row {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "nama", tint = Color.White)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = properti.status_properti,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}