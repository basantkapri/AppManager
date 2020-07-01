package com.bashu.kapri.appmanager.adapter

import com.bashu.kapri.appmanager.model.ApplicationDetail


interface ItemSelectedListener {
    fun onItemSelected(applicationDetail: ApplicationDetail)
}