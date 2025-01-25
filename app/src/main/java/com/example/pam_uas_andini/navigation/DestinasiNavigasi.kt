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

// MANAJER
object DestinasiHomeManajer : DestinasiNavigasi {
    override val route = "homeManajer"
    override val titleRes: String = "Home Manajer"
    const val IDMANAJER = "id"
    val routesWithArg = "$route/{$IDMANAJER}"
}

object DestinasiInsertManajer : DestinasiNavigasi {
    override val route = "insertManajer"
    override val titleRes: String = "Tambah Manajer"
    const val IDMANAJER = "id"
    val routesWithArg = "$route/{$IDMANAJER}"
}

object DestinasiDetailManajer : DestinasiNavigasi {
    override val route = "detailManajer"
    override val titleRes = "Detail Manajer"
    const val IDMANAJER = "id_manajer"
    val routesWithArg = "$route/{$IDMANAJER}"
}

object DestinasiUpdateManajer : DestinasiNavigasi {
    override val route = "updateManajer"
    override val titleRes = "Update Manajer"
    const val IDMANAJER = "id_manajer"
    val routesWithArg = "$route/{$IDMANAJER}"
}
