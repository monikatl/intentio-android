package com.example.intentio.ui.main.divines

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intentio.R

class DivinesFragment : Fragment() {

    companion object {
        fun newInstance() = DivinesFragment()
    }

    private lateinit var viewModel: DivinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_divines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DivinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}