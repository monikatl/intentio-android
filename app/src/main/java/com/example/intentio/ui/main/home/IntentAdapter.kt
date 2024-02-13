package com.example.intentio.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intentio.data.Intent
import com.example.intentio.databinding.IntentListItemBinding

class IntentAdapter(var datalist:List<Intent>):RecyclerView.Adapter<IntentAdapter.IntentHolder>() {


    class IntentHolder(val binding: IntentListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(intent: Intent) {
            binding.intent = intent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntentHolder {
        val binding = IntentListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return IntentHolder(binding)
    }

    override fun onBindViewHolder(holder: IntentHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}