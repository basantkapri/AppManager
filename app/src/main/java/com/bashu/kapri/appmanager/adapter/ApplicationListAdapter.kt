package com.bashu.kapri.appmanager.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.utils.AlertUtil
import com.bashu.kapri.appmanager.utils.AppHelper
import com.bashu.kapri.appmanager.utils.ImageHelper

class ApplicationListAdapter(
    private val context: Activity,
    private val fragment: Fragment,
    private var applicationList: MutableList<ApplicationDetail>
) :
    RecyclerView.Adapter<ApplicationListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var appIcon: ImageView = view.findViewById(R.id.appIcon)
        var appName: TextView = view.findViewById(R.id.appName)
        var packageName: TextView = view.findViewById(R.id.packageName)
        var versionNo: TextView = view.findViewById(R.id.versionNo)
        var installedOn: TextView = view.findViewById(R.id.installed_On)
        var lastUpdatedOn: TextView = view.findViewById(R.id.last_Updated_on)
        var btnAppDetail: Button = view.findViewById(R.id.btnAppDetail)
        var btnOpen: Button = view.findViewById(R.id.btnOpen)
        var btnUninstall: Button = view.findViewById(R.id.btnUninstall)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.application_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val application = applicationList[position]
        val packageName = application.appPackage
        viewHolder.appName.text = application.appName
        viewHolder.packageName.text = packageName
        viewHolder.versionNo.text = application.appVersion
        viewHolder.installedOn.text = application.installedOn
        viewHolder.lastUpdatedOn.text = application.lastUpdatedOn

        ImageHelper.showImage(
            context,
            application.appDrawableURI,
            viewHolder.appIcon
        )

        viewHolder.btnAppDetail.setOnClickListener {
            AppHelper.openApplicationDetail(context, it, packageName)
        }

        viewHolder.btnOpen.setOnClickListener {
            AppHelper.openApplication(context, it, packageName)
        }

        viewHolder.btnUninstall.setOnClickListener {
            AppHelper.uninstallApplication(fragment, it, packageName)
        }

        viewHolder.appIcon.setOnClickListener {
            if (application.list.isNullOrEmpty()) {
                AlertUtil.showAlert(it, "No Permission found...")
            } else {
                AlertUtil.showAlert(it, "${application.list?.get(0)}")
            }
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