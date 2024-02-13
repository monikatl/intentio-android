package com.example.intentio.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intentio.data.Intent
import com.example.intentio.databinding.IntentListItemBinding

class IntentAdapter():RecyclerView.Adapter<IntentAdapter.IntentHolder>() {

    lateinit var datalist:List<Intent>

    fun setList(datalist:List<Intent>) {
        this.datalist = datalist
    }

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