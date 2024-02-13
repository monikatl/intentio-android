package com.example.intentio.ui.main.intent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.intentio.R
import com.example.intentio.databinding.FragmentHomeBinding
import com.example.intentio.databinding.FragmentIntentBinding

class IntentFragment : Fragment() {

    companion object {
        fun newInstance() = IntentFragment()
    }

    private lateinit var viewModel: IntentViewModel
    private lateinit var binding: FragmentIntentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntentBinding.inflate(inflater, container, false)
        binding.messIntentButton.setOnClickListener { findNavController().navigate(R.id.action_intentFragment_to_orderIntentionFragment)}
        binding.messIntent.setOnClickListener { showInfoDialog() }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntentViewModel::class.java)

        // TODO: Use the ViewModel
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Intencja Mszy Świętej")
            .setMessage("fksdjdsjkfjndlkfnjklnseklfjl;ks fksljefls fjskjfkl sjfjsfk jsj/dflkjs/ dfkkd/jfk sfsjdlfjasflk dflj sa.dlkfj fhldsjv soda fsdfkjsd")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

}