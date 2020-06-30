package com.bashu.kapri.appmanager.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.adapter.SectionsPagerAdapter
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.utils.AlertUtil
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {

    private val viewModel by viewModels<PageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                requireActivity().supportFragmentManager,
                ApplicationData()
            )

        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewModel.data.observe(viewLifecycleOwner, Observer<ApplicationData> {
            sectionsPagerAdapter.updateList(it)
        })

        btnRefresh.setOnClickListener { view ->
            AlertUtil.showAlert(view, "Refreshing...")
            viewModel.getApplicationList()
        }
        viewModel.getApplicationList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100)
            viewModel.getApplicationList()
    }
}