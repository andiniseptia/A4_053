package com.example.pam_uas_andini.repository.jenis

import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.JenisDetailResponse
import com.example.pam_uas_andini.model.JenisResponse
import com.example.pam_uas_andini.service_api.JenisService
import okio.IOException

interface JenisRepository {
    suspend fun getJenis(): JenisResponse
    suspend fun insertJenis(jenis: Jenis)
    suspend fun updateJenis(id_jenis: String, jenis: Jenis)
    suspend fun deleteJenis(id_jenis: String)
    suspend fun getJenisById(id_jenis: String): JenisDetailResponse
}
