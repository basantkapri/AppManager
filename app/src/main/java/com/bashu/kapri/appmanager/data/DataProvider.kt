package com.bashu.kapri.appmanager.data

import android.app.Application
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.utils.CommonUtils

class DataProvider(private val application: Application) {
    fun getAllInstalledApps(): ApplicationData {
        val resolveInfoList = AppManagerHelper.getApplicationResolver(application)

        var data = ApplicationData()
        for (resolveInfo in resolveInfoList) {
            val activityInfo = resolveInfo.activityInfo
            val packageName = activityInfo.applicationInfo.packageName.toString()
            val appName = AppManagerHelper.getAppName(packageName, application)
            val permission = AppManagerHelper.getPermissions(packageName, application)
            when {
                CommonUtils.isAppMatched(appName) -> {
                    data.harmfulAppSize = data.harmfulAppSize + 1
                    data.harmfulApps.add(
                        ApplicationDetail(
                            appName,
                            packageName,
                            AppManagerHelper.getInstalledOn(packageName, application),
                            AppManagerHelper.getLastUpdatedOn(packageName, application),
                            AppManagerHelper.getAppVersion(packageName, application),
                            AppManagerHelper.getAppIconURI(packageName, application),
                            CommonUtils.isAppMatched(appName),
                            permission
                        )
                    )
                }
                AppManagerHelper.isSystemApp(resolveInfo) -> {
                    data.systemAppSize = data.systemAppSize + 1
                    data.systemApps.add(
                        ApplicationDetail(
                            appName,
                            packageName,
                            AppManagerHelper.getInstalledOn(packageName, application),
                            AppManagerHelper.getLastUpdatedOn(packageName, application),
                            AppManagerHelper.getAppVersion(packageName, application),
                            AppManagerHelper.getAppIconURI(packageName, application),
                            false,
                            permission
                        )
                    )
                }
                else -> {
                    data.userAppSize = data.userAppSize + 1
                    data.userApps.add(
                        ApplicationDetail(
                            appName,
                            packageName,
                            AppManagerHelper.getInstalledOn(packageName, application),
                            AppManagerHelper.getLastUpdatedOn(packageName, application),
                            AppManagerHelper.getAppVersion(packageName, application),
                            AppManagerHelper.getAppIconURI(packageName, application),
                            false,
                            permission
                        )
                    )
                }
            }
            data = sortList(data)
        }
        return data
    }

    private fun sortList(data: ApplicationData): ApplicationData {
        data.systemApps.sortWith(Comparator { o1, o2 ->
            o1.appName.compareTo(
                o2.appName,
                ignoreCase = true
            )
        })

        data.userApps.sortWith(Comparator { o1, o2 ->
            o1.appName.compareTo(
                o2.appName,
                ignoreCase = true
            )
        })

        data.harmfulApps.sortWith(Comparator { o1, o2 ->
            o1.appName.compareTo(
                o2.appName,
                ignoreCase = true
            )
        })
        return data
    }
}