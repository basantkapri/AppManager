package com.bashu.kapri.appmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.utils.AppHelper
import com.bashu.kapri.appmanager.utils.ImageHelper

class PermissionAdapter(
    private val listView: ExpandableListView,
    private val _context: Context,
    var map: LinkedHashMap<String, ArrayList<ApplicationDetail>>
) : BaseExpandableListAdapter() {
    var keys: ArrayList<String> = ArrayList()

    init {
        fillKeys()
    }

    fun refreshList(hashMap: LinkedHashMap<String, ArrayList<ApplicationDetail>>) {
        map = hashMap
        fillKeys()
        notifyDataSetChanged()
    }

    private fun fillKeys() {
        keys.clear()
        if (map != null && map.size > 0)
            keys.addAll(map.keys)
    }

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return map[keys[groupPosition]]!!.size
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {

        val key = keys[groupPosition]
        val list = map[key]
        val applicationDetail = list!![childPosition]

        var itemView = convertView
        if (itemView == null)
            itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.permission_cell, parent, false)

        val appName = itemView!!.findViewById<TextView>(R.id.appName)
        val appIcon = itemView!!.findViewById<ImageView>(R.id.appIcon)

        appName.text = applicationDetail.appName

        ImageHelper.showImage(
            _context,
            applicationDetail.appDrawableURI,
            appIcon
        )

        itemView.setOnClickListener(View.OnClickListener {
            AppHelper.openApplicationDetail(_context, it, applicationDetail.appPackage)
        })

        return itemView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        var size = 0
        if (map?.get(keys[groupPosition]) != null)
            size = map[keys[groupPosition]]!!.size
        return size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.keys[groupPosition]
    }

    override fun getGroupCount(): Int {
        return this.keys.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        val permission = keys[groupPosition]

        var itemView = convertView
        if (itemView == null)
            itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.permission_list_layout, parent, false)

        var sortName: TextView = itemView!!.findViewById(R.id.sortName)
        var appName: TextView = itemView!!.findViewById(R.id.appName)
        var tvApplication: TextView = itemView!!.findViewById(R.id.tvApplication)
        var dots: ImageView = itemView!!.findViewById(R.id.dots)

        appName.text = permission
        try {
            var text = permission.substring(permission.lastIndexOf(".") + 1)
            text = text.replace("_", "\n")
            sortName.text = text
        } catch (e: Exception) {
            e.printStackTrace()
        }

        itemView.setOnClickListener(View.OnClickListener {
            if (!isExpanded) {
                listView.expandGroup(groupPosition)
                dots.rotation = 90f
            } else {
                dots.setImageResource(R.drawable.rightdatechevron)
                listView.collapseGroup(groupPosition)
                dots.rotation = 0f
            }
        })

        if (isExpanded)
            tvApplication.visibility = View.VISIBLE
        else
            tvApplication.visibility = View.GONE

        itemView.tag = permission
        return itemView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}