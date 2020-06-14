package com.bashu.kapri.appmanager.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object AlertUtil {
   private var snackbar: Snackbar? = null
    fun showAlert(view: View, text: String) {
        if (snackbar != null)
            snackbar?.dismiss()

        snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        snackbar?.show()
    }
}