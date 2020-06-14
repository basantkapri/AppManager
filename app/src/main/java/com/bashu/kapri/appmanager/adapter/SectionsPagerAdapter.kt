package com.bashu.kapri.appmanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.view.PlaceholderFragment

class SectionsPagerAdapter(fm: FragmentManager, var data: ApplicationData) :
    FragmentStatePagerAdapter(fm) {
    val list = ArrayList<PlaceholderFragment>()
    override fun getItem(pos: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        val fragment = PlaceholderFragment(if (pos == 0) data!!.userApps else data!!.systemApps)
        list.add(fragment)
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data!!.arrAppType[position] + " (" + (if (position == 0) data.userAppSize else data.systemAppSize) + ")"
    }

    override fun getCount(): Int {
        return if (data.userAppSize > 0 || data.systemAppSize > 0) 2 else 0
    }

    fun updateList(it: ApplicationData) {
        data = it
        notifyDataSetChanged()

        if (list.size > 0) {
            for ((pos, fragment) in list.withIndex()) {
                fragment.refreshData(if (pos == 0) data!!.userApps else data!!.systemApps)
            }
        }
    }
}