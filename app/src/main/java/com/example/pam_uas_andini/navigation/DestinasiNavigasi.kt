package com.example.pam_uas_andini.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

// HOME
object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes: String = "Home"
}
