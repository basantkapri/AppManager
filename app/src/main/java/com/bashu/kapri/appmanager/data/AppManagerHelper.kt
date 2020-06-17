package com.bashu.kapri.appmanager.data

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri

object AppManagerHelper {

    fun getApplicationResolver(application: Application): MutableList<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        return application.packageManager.queryIntentActivities(intent, 0)
    }

    fun getAppIconURI(packageName: String, application: Application): Uri {
        var resUri = Uri.EMPTY
        try {
            val pm = application.packageManager
            val appInfo = pm.getApplicationInfo(packageName, 0)
            if (appInfo.icon != 0)
                resUri = Uri.parse("android.resource://" + packageName + "/" + appInfo.icon)

        } catch (e: PackageManager.NameNotFoundException) {
            resUri = Uri.EMPTY
            e.printStackTrace()
        }

        return resUri
    }

    fun isSystemApp(resolveInfo: ResolveInfo): Boolean {
        return resolveInfo.activityInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    fun getAppName(packageName: String, application: Application): String {
        var name = "N/A"
        try {
            val manager = application.packageManager
            val applicationInfo = manager.getApplicationInfo(packageName, 0)
            if (applicationInfo != null)
                name = manager.getApplicationLabel(applicationInfo) as String

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return name
    }

    fun getAppVersion(packageName: String, application: Application): String {
        var versionName = "N/A"
        try {
            val packageManager = application.packageManager
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)

            if (applicationInfo != null)
                versionName = packageManager.getPackageInfo(packageName, 0).versionName

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "V : $versionName"
    }

    fun getInstalledOn(packageName: String, application: Application): String {
        var installedOn = "N/A"
        try {
            val manager = application.packageManager
            val info = manager.getPackageInfo(packageName, 0)
            if (info != null) {
                val milli = info.firstInstallTime
                installedOn = "Installed On : " + DateHelper.getFormattedDate(milli)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return installedOn
    }

    fun getLastUpdatedOn(packageName: String, application: Application): String {
        var lastUpdatedOn = "N/A"
        try {
            val manager = application.packageManager
            val info = manager.getPackageInfo(packageName, 0)
            if (info != null) {
                val milli = info.lastUpdateTime
                lastUpdatedOn = "Updated On : " + DateHelper.getFormattedDate(milli)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return lastUpdatedOn
    }
}