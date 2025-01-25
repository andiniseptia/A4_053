package com.example.pam_uas_andini.dependenciesinjection

import com.example.pam_uas_andini.repository.jenis.JenisRepository
import com.example.pam_uas_andini.repository.jenis.NetworkJenisRepository
import com.example.pam_uas_andini.repository.manajer.ManajerRepository
import com.example.pam_uas_andini.repository.manajer.NetworkManajerRepository
import com.example.pam_uas_andini.repository.pemilik.NetworkPemilikRepository
import com.example.pam_uas_andini.repository.pemilik.PemilikRepository
import com.example.pam_uas_andini.repository.properti.NetworkPropertiRepository
import com.example.pam_uas_andini.repository.properti.PropertiRepository
import com.example.pam_uas_andini.service_api.JenisService
import com.example.pam_uas_andini.service_api.ManajerService
import com.example.pam_uas_andini.service_api.PemilikService
import com.example.pam_uas_andini.service_api.PropertiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContainerApp {
    val jenisRepository: JenisRepository
    val manajerRepository: ManajerRepository
    val pemilikRepository: PemilikRepository
    val propertiRepository: PropertiRepository
}

class PerusahaanContainer : ContainerApp {

    private val json = Json{ ignoreUnknownKeys = true }

    private val baseUrlPemilik = "http://10.0.2.2:3000/api/pemilik/"
    private val retrofitPemilik: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlPemilik).build()

    private val baseUrlJenis = "http://10.0.2.2:3000/api/jenis/"
    private val retrofitJenis: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlJenis).build()

    private val baseUrlManajer = "http://10.0.2.2:3000/api/manajer/"
    private val retrofitManajer: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlManajer).build()

    private val baseUrlProperti = "http://10.0.2.2:3000/api/properti/"
    private val retrofitProperti: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlProperti).build()

    private val jenisService: JenisService by lazy {
        retrofitJenis.create(JenisService::class.java)
    }

    private val manajerService: ManajerService by lazy {
        retrofitManajer.create(ManajerService::class.java)
    }

    private  val pemilikService: PemilikService by lazy {
        retrofitPemilik.create(PemilikService::class.java)
    }

    private val propertiService: PropertiService by lazy {
        retrofitProperti.create(PropertiService::class.java)
    }



}