package com.example.pam_uas_andini.ui.viewmodel

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_uas_andini.PerusahaanApp
import com.example.pam_uas_andini.ui.view.jenis.InsertJenisView
import com.example.pam_uas_andini.ui.view.pemilik.EntryBodyPemilik
import com.example.pam_uas_andini.ui.viewmodel.jenis.DetailJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.HomeJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.InsertJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.jenis.UpdateJenisViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.DetailManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.HomeManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.InsertManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.manajer.UpdateManajerViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.DetailPemilikViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.HomePemilikViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.InsertPemilikViewModel
import com.example.pam_uas_andini.ui.viewmodel.pemilik.UpdatePemilikViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.DetailPropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.HomePropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.InsertPropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.UpdatePropertiViewModel

/*import com.example.pam_uas_andini.ui.viewmodel.properti.DetailPropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.HomePropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.InsertPropertiViewModel
import com.example.pam_uas_andini.ui.viewmodel.properti.UpdatePropertiViewModel*/

object PenyediaViewModel {
    val Factory = viewModelFactory {

        //HOME
        initializer {
            HomeViewModel()
        }

        // PEMILIK
        initializer {
            HomePemilikViewModel(
                perusahaanApp().container.pemilikRepository
            )
        }
        initializer {
            InsertPemilikViewModel(
                perusahaanApp().container.pemilikRepository
            )
        }
        initializer {
            DetailPemilikViewModel(
                createSavedStateHandle(),
                perusahaanApp().container.pemilikRepository
            )
        }
        initializer {
            UpdatePemilikViewModel(
                savedStateHandle = createSavedStateHandle(),
                perusahaanApp().container.pemilikRepository
            )
        }

        // MANAJER
        initializer {
            HomeManajerViewModel(
                perusahaanApp().container.manajerRepository
            )
        }
        initializer {
            InsertManajerViewModel(
                perusahaanApp().container.manajerRepository
            )
        }
        initializer {
            DetailManajerViewModel(
                createSavedStateHandle(),
                perusahaanApp().container.manajerRepository
            )
        }
        initializer {
            UpdateManajerViewModel(
                savedStateHandle = createSavedStateHandle(),
                perusahaanApp().container.manajerRepository
            )
        }

        // JENIS
        initializer {
            HomeJenisViewModel(
                perusahaanApp().container.jenisRepository
            )
        }
        initializer {
            InsertJenisViewModel(
                perusahaanApp().container.jenisRepository
            )
        }
        initializer {
            DetailJenisViewModel(
                createSavedStateHandle(),
                perusahaanApp().container.jenisRepository
            )
        }
        initializer {
            UpdateJenisViewModel(
                savedStateHandle = createSavedStateHandle(),
                perusahaanApp().container.jenisRepository
            )
        }

    }
}

fun CreationExtras.perusahaanApp(): PerusahaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerusahaanApp)