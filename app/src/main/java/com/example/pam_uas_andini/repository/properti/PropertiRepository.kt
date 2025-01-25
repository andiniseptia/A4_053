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

