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

// PEMILIK
object DestinasiHomePemilik : DestinasiNavigasi {
    override val route = "homePemilik"
    override val titleRes: String = "Home Pemilik"
    const val IDPEMILIK = "id"
    val routesWithArg = "$route/{$IDPEMILIK}"
}

object DestinasiInsertPemilik : DestinasiNavigasi {
    override val route = "insertPemilik"
    override val titleRes: String = "Tambah Pemilik"
    const val IDPEMILIK = "id"
    val routesWithArg = "$route/{$IDPEMILIK}"
}

object DestinasiDetailPemilik : DestinasiNavigasi {
    override val route = "detailPemilik"
    override val titleRes = "Detail Pemilik"
    const val IDPEMILIK = "id_pemilik"
    val routesWithArg = "$route/{$IDPEMILIK}"
}

object DestinasiUpdatePemilik : DestinasiNavigasi {
    override val route = "updatePemilik"
    override val titleRes = "Update Pemilik"
    const val IDPEMILIK = "id_pemilik"
    val routesWithArg = "$route/{$IDPEMILIK}"
}
