package com.example.pam_uas_andini.model

import kotlinx.serialization.Serializable

@Serializable
data class Manajer (
    val id_manajer: String,
    val nama_manajer: String,
    val kontak_manajer: String
)

@Serializable
data class ManajerResponse(
    val status: Boolean,
    val message: String,
    val data: List<Manajer>
)

@Serializable
data class ManajerDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Manajer
)