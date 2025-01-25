package com.example.pam_uas_andini.service_api

import com.example.pam_uas_andini.model.Jenis
import com.example.pam_uas_andini.model.JenisResponse
import com.example.pam_uas_andini.model.Manajer
import com.example.pam_uas_andini.model.ManajerResponse
import com.example.pam_uas_andini.model.Pemilik
import com.example.pam_uas_andini.model.PemilikDetailResponse
import com.example.pam_uas_andini.model.PemilikResponse
import com.example.pam_uas_andini.model.Properti
import com.example.pam_uas_andini.model.PropertiDetailResponse
import com.example.pam_uas_andini.model.PropertiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PropertiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getProperti(): PropertiResponse

    @GET("{id_properti}")
    suspend fun getPropertiById(@Path("id_properti") id_properti:String): PropertiDetailResponse

    @POST("add")
    suspend fun insertProperti(@Body properti: Properti)

    @PUT("{id_properti}")
    suspend fun updateProperti(@Path("id_properti") id_properti: String, @Body properti: Properti)

    @DELETE("{id_properti}")
    suspend fun deleteProperti(@Path("id_properti") id_properti: String): Response<Void>

    //Dropdown
    @GET("jenis")
    suspend fun getJenis(): JenisResponse

    @GET("pemilik")
    suspend fun getPemilik(): PemilikResponse

    @GET("manajer")
    suspend fun getManajer(): ManajerResponse
}