package com.example.pam_uas_andini.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam_uas_andini.ui.view.HomeView
import com.example.pam_uas_andini.ui.view.jenis.DetailJenisView
import com.example.pam_uas_andini.ui.view.jenis.HomeJenisView
import com.example.pam_uas_andini.ui.view.jenis.InsertJenisView
import com.example.pam_uas_andini.ui.view.jenis.UpdateJenisView
import com.example.pam_uas_andini.ui.view.manajer.DetailManajerView
import com.example.pam_uas_andini.ui.view.manajer.HomeManajerView
import com.example.pam_uas_andini.ui.view.manajer.InsertManajerView
import com.example.pam_uas_andini.ui.view.manajer.UpdateManajerView
import com.example.pam_uas_andini.ui.view.pemilik.DetailPemilikView
import com.example.pam_uas_andini.ui.view.pemilik.HomePemilikView
import com.example.pam_uas_andini.ui.view.pemilik.InsertPemilikView
import com.example.pam_uas_andini.ui.view.pemilik.UpdatePemilikView
import com.example.pam_uas_andini.ui.view.properti.DetailPropertiView
import com.example.pam_uas_andini.ui.view.properti.HomePropertiView
import com.example.pam_uas_andini.ui.view.properti.InsertPropertiView
import com.example.pam_uas_andini.ui.view.properti.UpdatePropertiView
import com.example.pam_uas_andini.ui.viewmodel.HomeViewModel

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    println("PengelolaHalaman: Memulai NavHost dengan startDestination = ${DestinasiHome.route}")

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {


    }
}