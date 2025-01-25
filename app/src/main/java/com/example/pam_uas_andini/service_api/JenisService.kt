package com.example.pam_uas_andini.service_api

import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.JenisDetailResponse
import com.example.pam_uas_andini.model.JenisResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JenisService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getJenis(): JenisResponse

    @GET("{id_jenis}")
    suspend fun getJenisById(@Path("id_jenis") id_jenis:String): JenisDetailResponse

    @POST("add")
    suspend fun insertJenis(@Body jenis: Jenis)

    @PUT("{id_jenis}")
    suspend fun updateJenis(@Path("id_jenis") id_jenis: String, @Body jenis: Jenis)

    @DELETE("{id_jenis}")
    suspend fun deleteMahasiswa(@Path("id_jenis") id_jenis: String): Response<Void>
}