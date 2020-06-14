package com.bashu.kapri.appmanager.data

import android.app.Application
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail

class DataProvider(private val application: Application) {
    fun getAllInstalledApps(): ApplicationData {
        val resolveInfoList = AppManagerHelper.getApplicationResolver(application)

        val data = ApplicationData()
        for (resolveInfo in resolveInfoList) {

            val activityInfo = resolveInfo.activityInfo
            if (!AppManagerHelper.isSystemApp(resolveInfo)) {
                data.userAppSize = data.userAppSize + 1
                val packageName = activityInfo.applicationInfo.packageName.toString()
                data.userApps.add(
                    ApplicationDetail(
                        AppManagerHelper.getAppName(packageName, application),
                        packageName,
                        AppManagerHelper.getInstalledOn(packageName, application),
                        AppManagerHelper.getLastUpdatedOn(packageName, application),
                        AppManagerHelper.getAppVersion(packageName, application),
                        AppManagerHelper.getAppIconURI(packageName, application)
                    )
                )

                data.userApps.sortWith(Comparator { o1, o2 ->
                    o1.appName!!.compareTo(
                        o2.appName!!,
                        ignoreCase = true
                    )
                })

            } else {
                data.systemAppSize = data.systemAppSize + 1
                val packageName = activityInfo.applicationInfo.packageName.toString()
                data.systemApps.add(
                    ApplicationDetail(
                        AppManagerHelper.getAppName(packageName, application),
                        packageName,
                        AppManagerHelper.getInstalledOn(packageName, application),
                        AppManagerHelper.getLastUpdatedOn(packageName, application),
                        AppManagerHelper.getAppVersion(packageName, application),
                        AppManagerHelper.getAppIconURI(packageName, application)
                    )
                )

                data.systemApps.sortWith(Comparator { o1, o2 ->
                    o1.appName!!.compareTo(
                        o2.appName!!,
                        ignoreCase = true
                    )
                })
            }
        }
        return data
    }
}