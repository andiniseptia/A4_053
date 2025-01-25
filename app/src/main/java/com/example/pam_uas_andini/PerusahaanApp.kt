package com.example.pam_uas_andini

import android.app.Application
import com.example.pam_uas_andini.dependenciesinjection.ContainerApp
import com.example.pam_uas_andini.dependenciesinjection.PerusahaanContainer

class PerusahaanApp: Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = PerusahaanContainer()
    }
}