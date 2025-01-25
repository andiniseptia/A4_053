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

class NetworkJenisRepository(
    private val jenisApiService: JenisService
) : JenisRepository {

    override suspend fun insertJenis(jenis: Jenis) {
        jenisApiService.insertJenis(jenis)
    }

    override suspend fun updateJenis(id_jenis: String, jenis: Jenis) {
        jenisApiService.updateJenis(id_jenis, jenis)
    }

    override suspend fun deleteJenis(id_jenis: String) {
        try {
            val response = jenisApiService.deleteMahasiswa(id_jenis)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete jenis properti. HTTP Status Code : " +
                "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getJenis(): JenisResponse {
        return jenisApiService.getJenis()

    }

    override suspend fun getJenisById(id_jenis: String): JenisDetailResponse {
        return jenisApiService.getJenisById(id_jenis)
    }

}