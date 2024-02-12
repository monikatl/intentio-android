package com.example.intentio.ui.main.sacraments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intentio.R

class SacramentsFragment : Fragment() {

    companion object {
        fun newInstance() = SacramentsFragment()
    }

    private lateinit var viewModel: SacramentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sacraments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SacramentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}