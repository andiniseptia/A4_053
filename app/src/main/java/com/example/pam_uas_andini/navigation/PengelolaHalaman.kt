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
        // HOME
        composable(DestinasiHome.route) {
            println("Navigasi ke HomeView")
            val viewModel: HomeViewModel = viewModel() // Ambil instance ViewModel yang sama
            HomeView(
                navigateToPemilik = {
                    navController.navigate(DestinasiHomePemilik.route)
                },
                navigateToManajer = {
                    navController.navigate(DestinasiHomeManajer.route)
                },
                navigateToJenis = {
                    navController.navigate(DestinasiHomeJenis.route)
                },
                navigateToProperti = {
                    navController.navigate(DestinasiHomeProperti.route)
                },
                navController = navController,
                viewModel = viewModel // Pass instance ViewModel yang sama
            )
        }

        // PEMILIK
        composable(DestinasiHomePemilik.route) {
            println("Navigasi ke HomePemilikView")
            HomePemilikView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertPemilik.route)
                },
                onDetailClick = { id_pemilik ->
                    println("PengelolaHalaman: id_pemilik = $id_pemilik")
                navController.navigate("${DestinasiDetailPemilik.route}/$id_pemilik")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(DestinasiInsertPemilik.route) {
            println("Navigasi ke InsertPemilikView")
            InsertPemilikView(navigateBack = {
                navController.navigate(DestinasiHomePemilik.route) {
                    popUpTo(DestinasiHomePemilik.route) {
                        inclusive = true
                    }
                }
            })
        }


    }
}