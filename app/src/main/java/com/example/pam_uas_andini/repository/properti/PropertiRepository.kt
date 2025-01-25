package com.example.pam_uas_andini.repository.properti

import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.JenisResponse
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.ManajerResponse
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.PemilikResponse
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.model.PropertiDetailResponse
import com.example.pam_uas_andini.model.PropertiResponse
import com.example.pam_uas_andini.service_api.PropertiService
import okio.IOException

interface PropertiRepository {
    suspend fun getProperti(): PropertiResponse
    suspend fun insertProperti(properti: Properti)
    suspend fun updateProperti(id_properti: String, properti: Properti)
    suspend fun deleteProperti(id_properti: String)
    suspend fun getPropertiById(id_properti: String): PropertiDetailResponse

    //Untuk dropdown
    suspend fun getJenis(): JenisResponse
    suspend fun getPemilik(): PemilikResponse
    suspend fun getManajer(): ManajerResponse
}

class NetworkPropertiRepository(
    private val propertiApiService: PropertiService
) : PropertiRepository {

    override suspend fun insertProperti(properti: Properti) {
        propertiApiService.insertProperti(properti)
    }

    override suspend fun updateProperti(id_properti: String, properti: Properti) {
        propertiApiService.updateProperti(id_properti, properti)
    }

    override suspend fun deleteProperti(id_properti: String) {
        try {
            val response = propertiApiService.deleteProperti(id_properti)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete properti. HTTP Status Code : " +
                "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getProperti(): PropertiResponse {
        return propertiApiService.getProperti()
    }

    override suspend fun getPropertiById(id_properti: String): PropertiDetailResponse {
        return propertiApiService.getPropertiById(id_properti)
    }

    override suspend fun getJenis(): JenisResponse {
        return propertiApiService.getJenis()
    }

    override suspend fun getPemilik(): PemilikResponse {
        return propertiApiService.getPemilik()
    }

    override suspend fun getManajer(): ManajerResponse {
        return propertiApiService.getManajer()
    }

}