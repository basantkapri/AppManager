package com.bashu.kapri.appmanager.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bashu.kapri.appmanager.data.DataProvider
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.utils.CommonUtils

class PermissionsViewModel(application: Application) : AndroidViewModel(application) {
    val applicationData = MutableLiveData<LinkedHashMap<String, ArrayList<ApplicationDetail>>>()

    fun getApplicationList() {
        val data = DataProvider(getApplication()).getAllInstalledApps(true)

        var appMap = LinkedHashMap<String, ArrayList<ApplicationDetail>>()
        appMap = getMap(appMap, data.harmfulApps)
        appMap = getMap(appMap, data.systemApps)
        appMap = getMap(appMap, data.userApps)
        applicationData.value = appMap
    }

    private fun getMap(
        appMap: LinkedHashMap<String, ArrayList<ApplicationDetail>>,
        list: MutableList<ApplicationDetail>
    ): LinkedHashMap<String, ArrayList<ApplicationDetail>> {
        list.forEach { app ->
            app.list?.forEach {
                if (CommonUtils.isDangerous(it)) {
                    var list = appMap[it]
                    if (list == null)
                        list = ArrayList()

                    list.add(app)
                    appMap[it] = list
                }
            }
        }

        return appMap
    }
}