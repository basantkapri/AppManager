package com.bashu.kapri.appmanager.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bashu.kapri.appmanager.R
import com.bashu.kapri.appmanager.adapter.ItemSelectedListener
import com.bashu.kapri.appmanager.adapter.PermissionAdapter
import com.bashu.kapri.appmanager.model.ApplicationData
import com.bashu.kapri.appmanager.model.ApplicationDetail
import com.bashu.kapri.appmanager.ui.home.PageViewModel
import com.bashu.kapri.appmanager.utils.AlertUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionsFragment : Fragment(R.layout.fragment_permission) {
    private val viewModel by viewModels<PermissionsViewModel>()
    private var adapter: PermissionAdapter? = null
    private var applicationMap = LinkedHashMap<String, ArrayList<ApplicationDetail>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.applicationData.observe(
            viewLifecycleOwner,
            Observer<LinkedHashMap<String, ArrayList<ApplicationDetail>>> {
                applicationMap = it
                adapter?.refreshList(applicationMap)
            })

        adapter = PermissionAdapter(expList, requireActivity(), LinkedHashMap())
        expList!!.setAdapter(adapter)
        viewModel.getApplicationList()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var keys: ArrayList<String> = ArrayList()
                keys.addAll(applicationMap.keys)
                val map = LinkedHashMap<String, ArrayList<ApplicationDetail>>()
                val text = "$p0".toLowerCase()
                keys.forEach {
                    if (it.toLowerCase().contains(text))
                        applicationMap[it]?.let { it1 -> map.put(it, it1) }
                }

                if (TextUtils.isEmpty(text))
                    adapter?.refreshList(applicationMap)
                else
                    adapter?.refreshList(map)
            }
        })
    }
}