package com.example.pam_uas_andini.repository.manajer

import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.ManajerDetailResponse
import com.example.pam_uas_andini.model.ManajerResponse
import com.example.pam_uas_andini.service_api.ManajerService
import okio.IOException

interface ManajerRepository {
    suspend fun getManajer(): ManajerResponse
    suspend fun insertManajer(manajer: Manajer)
    suspend fun updateManajer(id_manajer: String, manajer: Manajer)
    suspend fun deleteManajer(id_manajer: String)
    suspend fun getManajerById(id_manajer: String): ManajerDetailResponse
}

class NetworkManajerRepository(
    private val manajerApiService: ManajerService
) : ManajerRepository {

    override suspend fun insertManajer(manajer: Manajer) {
        manajerApiService.insertManajer(manajer)
    }

    override suspend fun updateManajer(id_manajer: String, manajer: Manajer) {
        manajerApiService.updateManajer(id_manajer, manajer)
    }

    override suspend fun deleteManajer(id_manajer: String) {
        try {
            val response = manajerApiService.deleteManajer(id_manajer)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete manajer. HTTP Status Code : " +
                "${response.code()}")
            } else  {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getManajer(): ManajerResponse {
        return manajerApiService.getManajer()
    }

    override suspend fun getManajerById(id_manajer: String): ManajerDetailResponse {
        return manajerApiService.getManajerById(id_manajer)
    }

}