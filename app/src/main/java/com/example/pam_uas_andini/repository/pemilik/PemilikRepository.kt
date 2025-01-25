package com.example.pam_uas_andini.repository.pemilik

import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.PemilikDetailResponse
import com.example.pam_uas_andini.model.PemilikResponse
import com.example.pam_uas_andini.service_api.PemilikService
import okio.IOException

interface PemilikRepository {
    suspend fun getPemilik(): PemilikResponse
    suspend fun insertPemilik(pemilik: Pemilik)
    suspend fun updatePemilik(id_pemilik: String, pemilik: Pemilik)
    suspend fun deletePemilik(id_pemilik: String)
    suspend fun getPemilikById(id_pemilik: String): PemilikDetailResponse
}
