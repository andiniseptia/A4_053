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
