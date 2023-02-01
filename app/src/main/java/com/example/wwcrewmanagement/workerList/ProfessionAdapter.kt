package com.example.wwcrewmanagement.workerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wwcrewmanagement.R
import com.example.wwcrewmanagement.databinding.FilterItemBinding

class ProfessionAdapter(private val filterWorkers: (String) -> Unit) :
    RecyclerView.Adapter<ProfessionAdapter.ViewHolder>() {

    var professionsToFilter: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<FilterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.filter_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.jobTv.text = professionsToFilter[position]

        holder.binding.root.setOnClickListener {
            filterWorkers(professionsToFilter[position])
        }

    }

    override fun getItemCount(): Int = professionsToFilter.size

    class ViewHolder(val binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root)

}
