package com.bashu.kapri.appmanager.model

import android.net.Uri

data class ApplicationDetail(
    var appName: String,
    var appPackage: String,
    var installedOn: String,
    var lastUpdatedOn: String,
    var appVersion: String,
    var appDrawableURI: Uri,
    var isHarmful: Boolean = false,
    var list: Array<String>? = null
)
