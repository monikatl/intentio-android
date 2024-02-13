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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentio.R
import com.example.intentio.data.Intent
import com.example.intentio.data.IntentType
import com.example.intentio.data.Priest
import com.example.intentio.data.User
import com.example.intentio.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val intents: List<Intent> = listOf(
            Intent(IntentType.SINGLE, "O Boże błogosławieństwo dla Adama Nowaka w trzydziestą rocznicę urodzin.", "24:05:2019 7:00", User("Ktoś"), Priest()),
            Intent(IntentType.SINGLE, "O Boże błogosławieństwo dla Adama Nowaka w trzydziestą rocznicę urodzin.", "24:05:2019 7:00", User("Ktoś"), Priest()),
            Intent(IntentType.SINGLE, "O Boże błogosławieństwo dla Adama Nowaka w trzydziestą rocznicę urodzin.", "24:05:2019 7:00", User("Ktoś"), Priest())
        )

        val adapter = IntentAdapter(intents)
        binding.intentsRecyclerview.adapter = adapter


        val navController = findNavController()

        binding.calendar.setOnClickListener { navController.navigate(R.id.action_nav_home_to_calendarFragment) }
        binding.intents.setOnClickListener { navController.navigate(R.id.action_nav_home_to_intentFragment) }
        binding.sacraments.setOnClickListener { navController.navigate(R.id.action_nav_home_to_sacramentsFragment) }
        binding.others.setOnClickListener { navController.navigate(R.id.action_nav_home_to_othersFragment) }




//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }



}