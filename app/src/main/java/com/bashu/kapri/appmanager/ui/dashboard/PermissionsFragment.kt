package com.bashu.kapri.appmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bashu.kapri.appmanager.R

class PermissionsFragment : Fragment() {

    private lateinit var permissionsViewModel: PermissionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        permissionsViewModel =
            ViewModelProviders.of(this).get(PermissionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_permission, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        permissionsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}