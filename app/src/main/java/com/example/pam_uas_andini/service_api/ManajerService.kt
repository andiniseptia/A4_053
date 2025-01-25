package com.example.pam_uas_andini.service_api

import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.JenisDetailResponse
import com.example.pam_uas_andini.model.JenisResponse
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.ManajerDetailResponse
import com.example.pam_uas_andini.model.ManajerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ManajerService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getManajer(): ManajerResponse

    @GET("{id_manajer}")
    suspend fun getManajerById(@Path("id_manajer") id_manajer:String): ManajerDetailResponse

    @POST("add")
    suspend fun insertManajer(@Body manajer: Manajer)

    @PUT("{id_manajer}")
    suspend fun updateManajer(@Path("id_manajer") id_manajer: String, @Body manajer: Manajer)

    @DELETE("{id_manajer}")
    suspend fun deleteManajer(@Path("id_manajer") id_manajer: String): Response<Void>
}