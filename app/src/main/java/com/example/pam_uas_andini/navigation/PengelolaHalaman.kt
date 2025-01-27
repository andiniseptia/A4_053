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
import com.example.pam_uas_andini.ui.view.jenis.HomeJenisFilteredView
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
                },
                navigateToEdit = { id_pemilik ->
                    navController.navigate("${DestinasiUpdatePemilik.route}/$id_pemilik")
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

        composable(
            DestinasiDetailPemilik.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPemilik.IDPEMILIK) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id_pemilik = backStackEntry.arguments?.getString(DestinasiDetailPemilik.IDPEMILIK)
            DetailPemilikView(
                onBack = {
                    navController.popBackStack()
                },
                onEditClick = {
                    id_pemilik?.let { id ->
                        navController.navigate("${DestinasiUpdatePemilik.route}/$id") // Pass the id_pemilik here
                    }
                },
                modifier = modifier,
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            DestinasiUpdatePemilik.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePemilik.IDPEMILIK) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_pemilik = it.arguments?.getString(DestinasiUpdatePemilik.IDPEMILIK)
            id_pemilik?.let { id_pemilik ->
                UpdatePemilikView(
                    onBack = {navController.popBackStack()},
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        // MANAJER
        composable(DestinasiHomeManajer.route) {
            println("Navigasi ke HomeManajerView")
            HomeManajerView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertManajer.route)
                },
                onDetailClick = { id_manajer ->
                    println("PengelolaHalaman: id_manajer = $id_manajer")
                    navController.navigate("${DestinasiDetailManajer.route}/$id_manajer")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToEdit = { id_manajer ->
                    navController.navigate("${DestinasiUpdateManajer.route}/$id_manajer")
                }
            )
        }

        composable(DestinasiInsertManajer.route) {
            println("Navigasi ke InsertManajerView")
            InsertManajerView(navigateBack = {
                navController.navigate(DestinasiHomeManajer.route) {
                    popUpTo(DestinasiHomeManajer.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailManajer.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailManajer.IDMANAJER) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id_manajer = backStackEntry.arguments?.getString(DestinasiDetailManajer.IDMANAJER)
            DetailManajerView(
                onBack = {
                    navController.popBackStack()
                },
                onEditClick = {
                    id_manajer?.let { id ->
                        navController.navigate("${DestinasiUpdateManajer.route}/$id") // Pass the id_pemilik here
                    }
                },
                modifier = modifier,
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            DestinasiUpdateManajer.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateManajer.IDMANAJER) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_manajer = it.arguments?.getString(DestinasiUpdateManajer.IDMANAJER)
            id_manajer?.let { id_manajer ->
                UpdateManajerView(
                    onBack = {navController.popBackStack()},
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        // JENIS
        composable(DestinasiHomeJenis.route) {
            println("Navigasi ke HomeJenisView")
            HomeJenisView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertJenis.route)
                },
                onDetailClick = { id_jenis ->
                    println("PengelolaHalaman: id_jenis = $id_jenis")
                    navController.navigate("${DestinasiDetailJenis.route}/$id_jenis")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToEdit = { id_jenis ->
                    navController.navigate("${DestinasiUpdateJenis.route}/$id_jenis")
                }
            )
        }

        composable(DestinasiInsertJenis.route) {
            println("Navigasi ke InsertJenisView")
            InsertJenisView(navigateBack = {
                navController.navigate(DestinasiHomeJenis.route) {
                    popUpTo(DestinasiHomeJenis.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailJenis.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailJenis.IDJENIS) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id_jenis = backStackEntry.arguments?.getString(DestinasiDetailJenis.IDJENIS)
            DetailJenisView(
                onBack = {
                    navController.popBackStack()
                },
                onEditClick = {
                    id_jenis?.let { id_jenis ->
                        navController.navigate("${DestinasiUpdateJenis.route}/$id_jenis")
                    }
                },
                modifier = modifier,
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            DestinasiUpdateJenis.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateJenis.IDJENIS) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_jenis = it.arguments?.getString(DestinasiUpdateJenis.IDJENIS)
            id_jenis?.let { id_jenis ->
                UpdateJenisView(
                    onBack = {navController.popBackStack()},
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        // PROPERTI
        composable(DestinasiHomeProperti.route) {
            println("Navigasi ke HomePropertiView")
            HomePropertiView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsertProperti.route)
                },
                onDetailClick = { id_properti ->
                    println("PengelolaHalaman: id_properti = $id_properti")
                    navController.navigate("${DestinasiDetailProperti.route}/$id_properti")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToEdit = { id_properti ->
                    navController.navigate("${DestinasiUpdateProperti.route}/$id_properti")
                }
            )
        }
        composable(DestinasiInsertProperti.route) {
            println("Navigasi ke InsertPropertiView")
            InsertPropertiView(navigateBack = {
                navController.navigate(DestinasiHomeProperti.route) {
                    popUpTo(DestinasiHomeProperti.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailProperti.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailProperti.IDPROPERTI) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id_properti = backStackEntry.arguments?.getString(DestinasiDetailProperti.IDPROPERTI)
            DetailPropertiView(
                onBack = {
                    navController.popBackStack()
                },
                onEditClick = {
                    id_properti?.let { id_properti ->
                        navController.navigate("${DestinasiUpdateProperti.route}/$id_properti")
                    }
                },
                modifier = modifier,
                onDeleteClick = {
                    navController.popBackStack()
                },
                onJenisClick = { id_jenis -> navController.navigate("${DestinasiHomeJenisFiltered.route}/$id_jenis")
                },
                navController = navController
            )
        }

        composable(
            DestinasiHomeJenisFiltered.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiHomeJenisFiltered.IDJENIS) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id_jenis = backStackEntry.arguments?.getString(DestinasiHomeJenisFiltered.IDJENIS)
            HomeJenisFilteredView(
                id_jenis = id_jenis ?: return@composable,
                navigateToDetail = { id_jenis_detail ->
                    navController.navigate("${DestinasiDetailJenis.route}/$id_jenis_detail")
                },
                navigateBack = {
                    navController.popBackStack()
                },
                navController = navController
            )
        }

        composable(
            DestinasiUpdateProperti.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateProperti.IDPROPERTI) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_properti = it.arguments?.getString(DestinasiUpdateProperti.IDPROPERTI)
            id_properti?.let { id_properti ->
                UpdatePropertiView(
                    onBack = {navController.popBackStack()},
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}