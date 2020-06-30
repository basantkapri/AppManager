package com.bashu.kapri.appmanager.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.adapter.ApplicationListAdapter
import com.bashu.kapri.appmanager.model.ApplicationDetail
import kotlinx.android.synthetic.main.fragment_main.*

class PlaceholderFragment(private var applicationList: MutableList<ApplicationDetail>) :
    Fragment(R.layout.fragment_main) {
    private var scanAdapter: ApplicationListAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        scanAdapter = ApplicationListAdapter(requireActivity(), applicationList)

        app_list.apply {
            adapter = scanAdapter
            setHasFixedSize(true)
        }
    }

    fun refreshData(applicationList: MutableList<ApplicationDetail>) {
        this.applicationList = applicationList
        scanAdapter?.refreshList(applicationList)
    }
}