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

