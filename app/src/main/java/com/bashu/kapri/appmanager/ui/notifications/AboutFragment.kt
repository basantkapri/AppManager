package com.bashu.kapri.appmanager.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bashu.kapri.appmanager.BuildConfig
import com.bashu.kapri.appmanager.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(R.layout.fragment_about) {
    private val viewModel by viewModels<AboutViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_notifications.text =
            "Application : " + "Application Manager" +
                    "\nVersion : " + BuildConfig.VERSION_NAME +
                    "\nSupport : bashu.nxp@gmail.com"
    }
}