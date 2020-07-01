package com.bashu.kapri.appmanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.ui.home.PlaceholderFragment

class SectionsPagerAdapter(
    fm: FragmentManager,
    private var data: ApplicationData,
    val fragment: Fragment
) :
    FragmentStatePagerAdapter(fm) {
    private val list = ArrayList<PlaceholderFragment>()
    override fun getItem(pos: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        val fragment =
            PlaceholderFragment(getList(pos), fragment)
        list.add(fragment)
        return fragment
    }

    override fun getPageTitle(pos: Int): CharSequence? {
        val size = when (pos) {
            0 ->
                data.userAppSize

            1 ->
                data.systemAppSize

            2 ->
                data.harmfulAppSize

            else ->
                data.harmfulAppSize
        }
        return data.arrAppType[pos] + "\n (" + size + ")"
    }

    override fun getCount(): Int {
        return 3
    }

    fun updateList(it: ApplicationData) {
        data = it
        notifyDataSetChanged()

        if (list.size > 0) {
            for ((pos, fragment) in list.withIndex()) {
                fragment.refreshData(getList(pos))
            }
        }
    }

    private fun getList(pos: Int): MutableList<ApplicationDetail> {
        return when (pos) {
            0 ->
                data.userApps

            1 ->
                data.systemApps

            2 ->
                data.harmfulApps

            else ->
                data.harmfulApps
        }
    }
}