package com.peliculas.injectiondependenceanddatapersistence.ui.ads

import android.app.Application
import com.google.android.gms.ads.MobileAds

class aplicationAds:Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}