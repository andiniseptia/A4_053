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

class NetworkPemilikRepository(
    private val pemilikApiService: PemilikService
) : PemilikRepository {

    override suspend fun insertPemilik(pemilik: Pemilik) {
        pemilikApiService.insertPemilik(pemilik)
    }

    override suspend fun updatePemilik(id_pemilik: String, pemilik: Pemilik) {
        pemilikApiService.updatePemilik(id_pemilik, pemilik)
    }

    override suspend fun deletePemilik(id_pemilik: String) {
        try {
            val response = pemilikApiService.deletePemilik(id_pemilik)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pemilik. HTTP Status Code : "+
                "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getPemilik(): PemilikResponse {
        return pemilikApiService.getPemilik()
    }

    override suspend fun getPemilikById(id_pemilik: String): PemilikDetailResponse {
        return pemilikApiService.getPemilikById(id_pemilik)
    }
}