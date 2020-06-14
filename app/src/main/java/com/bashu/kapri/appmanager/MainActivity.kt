package com.bashu.kapri.appmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.bashu.kapri.appmanager.adapter.SectionsPagerAdapter
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.utils.AlertUtil
import com.bashu.kapri.appmanager.viewmodel.PageViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<PageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                supportFragmentManager,
                ApplicationData()
            )

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewModel.data.observe(this, Observer<ApplicationData> {
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