package com.bashu.kapri.appmanager.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bashu.kapri.appmanager.data.DataProvider
import com.bashu.kapri.appmanager.model.ApplicationData

class PageViewModel(application: Application) : AndroidViewModel(application) {
    val data = MutableLiveData<ApplicationData>()

    fun getApplicationList() {
        data.value = DataProvider(getApplication()).getAllInstalledApps(false)
    }
}