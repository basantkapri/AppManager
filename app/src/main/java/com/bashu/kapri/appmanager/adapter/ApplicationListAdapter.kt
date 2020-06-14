package com.bashu.kapri.appmanager.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.utils.AppHelper
import com.bashu.kapri.appmanager.utils.ImageHelper

class ApplicationListAdapter(
    private val context: Activity,
    private var applicationList: MutableList<ApplicationDetail>
) :
    RecyclerView.Adapter<ApplicationListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var appIcon: ImageView = view.findViewById(R.id.appIcon)
        var appName: TextView = view.findViewById(R.id.appName)
        var packageName: TextView = view.findViewById(R.id.packageName)
        var versionNo: TextView = view.findViewById(R.id.versionNo)
        var installed_On: TextView = view.findViewById(R.id.installed_On)
        var last_Updated_on: TextView = view.findViewById(R.id.last_Updated_on)
        var btnAppDetail: Button = view.findViewById(R.id.btnAppDetail)
        var btnOpen: Button = view.findViewById(R.id.btnOpen)
        var btnUninstall: Button = view.findViewById(R.id.btnUninstall)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view2 = LayoutInflater.from(context).inflate(R.layout.application_cell, parent, false)
        return ViewHolder(view2)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val packageName = applicationList[position].appPackage
        viewHolder.appName.text = applicationList[position].appName
        viewHolder.packageName.text = packageName
        viewHolder.versionNo.text = applicationList[position].appVersion
        viewHolder.installed_On.text = applicationList[position].installedOn
        viewHolder.last_Updated_on.text = applicationList[position].lastUpdatedOn

        ImageHelper.showImage(
            context,
            applicationList[position].appDrawableURI,
            viewHolder.appIcon
        )

        viewHolder.btnAppDetail.setOnClickListener {
            AppHelper.openApplicationDetail(context, it, packageName)
        }

        viewHolder.btnOpen.setOnClickListener {
            AppHelper.openApplication(context, it, packageName)
        }

        viewHolder.btnUninstall.setOnClickListener {
            AppHelper.uninstallApplication(context, it, packageName)
        }
    }

    override fun getItemCount(): Int {
        return applicationList.size
    }

    fun refreshList(applicationList: MutableList<ApplicationDetail>) {
        this.applicationList = applicationList
        notifyDataSetChanged()
    }
}