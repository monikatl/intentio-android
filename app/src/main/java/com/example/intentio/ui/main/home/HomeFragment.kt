package com.example.intentio.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.intentio.R
import com.example.intentio.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navController = findNavController()

        binding.calendar.setOnClickListener { navController.navigate(R.id.action_nav_home_to_calendarFragment) }
        binding.intents.setOnClickListener { navController.navigate(R.id.action_nav_home_to_intentFragment) }
        binding.sacraments.setOnClickListener { navController.navigate(R.id.action_nav_home_to_sacramentsFragment) }
        binding.others.setOnClickListener { navController.navigate(R.id.action_nav_home_to_othersFragment) }


//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}