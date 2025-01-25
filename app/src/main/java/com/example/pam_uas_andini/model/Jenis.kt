package com.example.pam_uas_andini.model

import kotlinx.serialization.Serializable

@Serializable
data class Jenis (
    val id_jenis: String,
    val nama_jenis: String,
    val deskripsi_jenis: String
)

@Serializable
data class JenisResponse(
    val status: Boolean,
    val message: String,
    val data: List<Jenis>
)

@Serializable
data class JenisDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Jenis
)