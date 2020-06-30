package com.bashu.kapri.appmanager.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View

object AppHelper {

    fun openApplication(context: Context, view: View, packageName: String) {
        try {
            val intent =
                context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
            } else {
                AlertUtil.showAlert(view, "Something went wrong, please try again.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            AlertUtil.showAlert(view, "Something went wrong, please try again.")
        }
    }

    fun openApplicationDetail(context: Context, view: View, packageName: String) {
        try {
            //Open the specific App Info page:
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            context.startActivity(intent)

        } catch (e: Exception) {
            AlertUtil.showAlert(view, "Something went wrong, please try again.")
        }
    }

    fun uninstallApplication(context: Activity, view: View, packageName: String) {
        try {
            val uri = Uri.parse("package:$packageName")
            val uIntent = Intent(Intent.ACTION_DELETE, uri)
            context.startActivityForResult(uIntent, 100)
        } catch (e: Exception) {
            e.printStackTrace()
            AlertUtil.showAlert(view, "Application can't be uninstalled")
        }
    }
}