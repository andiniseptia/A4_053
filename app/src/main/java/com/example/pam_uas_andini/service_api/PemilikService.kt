package com.example.pam_uas_andini.service_api

import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.PemilikDetailResponse
import com.example.pam_uas_andini.model.PemilikResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemilikService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getPemilik(): PemilikResponse

    @GET("{id_pemilik}")
    suspend fun getPemilikById(@Path("id_pemilik") id_pemilik:String): PemilikDetailResponse

    @POST("add")
    suspend fun insertPemilik(@Body pemilik: Pemilik)

    @PUT("{id_pemilik}")
    suspend fun updatePemilik(@Path("id_pemilik") id_pemilik: String, @Body pemilik: Pemilik)

    @DELETE("{id_pemilik}")
    suspend fun deletePemilik(@Path("id_pemilik") id_pemilik: String): Response<Void>
}

