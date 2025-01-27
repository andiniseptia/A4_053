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
    override val titleRes: String = "Daftar Pemilik"
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
    override val titleRes: String = "Daftar Manajer"
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

// JENIS
object DestinasiHomeJenis : DestinasiNavigasi {
    override val route = "homeJenis"
    override val titleRes: String = "Daftar Jenis Properti"
    const val IDJENIS = "id_jenis"
    val routesWithArg = "$route/{$IDJENIS}"
}

object DestinasiInsertJenis : DestinasiNavigasi {
    override val route = "insertJenis"
    override val titleRes: String = "Tambah Jenis"
    const val IDJENIS = "id_jenis"
    val routesWithArg = "$route/{$IDJENIS}"
}

object DestinasiDetailJenis : DestinasiNavigasi {
    override val route = "detailJenis"
    override val titleRes = "Detail Jenis"
    const val IDJENIS = "id_jenis"
    val routesWithArg = "$route/{$IDJENIS}"
}

object DestinasiUpdateJenis : DestinasiNavigasi {
    override val route = "updateJenis"
    override val titleRes = "Update Jenis"
    const val IDJENIS = "id_jenis"
    val routesWithArg = "$route/{$IDJENIS}"
}

// PROPERTI
object DestinasiHomeProperti : DestinasiNavigasi {
    override val route = "homeProperti"
    override val titleRes: String = "Daftar Properti"
    const val IDPROPERTI = "id_properti"
    val routesWithArg = "$route/{$IDPROPERTI}"
}

object DestinasiInsertProperti : DestinasiNavigasi {
    override val route = "insertProperti"
    override val titleRes: String = "Tambah Properti"
    const val IDPROPERTI = "id_properti"
    val routesWithArg = "$route/{$IDPROPERTI}"
}

object DestinasiDetailProperti : DestinasiNavigasi {
    override val route = "detailPropertis"
    override val titleRes = "Detail Properti"
    const val IDPROPERTI = "id_properti"
    val routesWithArg = "$route/{$IDPROPERTI}"
}

object DestinasiUpdateProperti : DestinasiNavigasi {
    override val route = "updateProperti"
    override val titleRes = "Update Properti"
    const val IDPROPERTI = "id_properti"
    val routesWithArg = "$route/{$IDPROPERTI}"
}
