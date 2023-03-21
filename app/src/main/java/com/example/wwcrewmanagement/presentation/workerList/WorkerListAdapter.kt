package com.example.wwcrewmanagement.presentation.workerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wwcrewmanagement.R
import com.example.wwcrewmanagement.databinding.WorkerItemBinding
import com.example.wwcrewmanagement.model.Worker

class WorkerAdapter(private val navigate: (Worker) -> Unit) :
    RecyclerView.Adapter<WorkerAdapter.ViewHolder>() {

    var workers: List<Worker> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<WorkerItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.worker_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worker = workers[position]
        holder.binding.workerName.text = worker.name
        holder.binding.workerPosition.text = worker.profession

        Glide.with(holder.itemView.context)
            .load(worker.image)
            .into(holder.binding.workerImage)

        holder.binding.root.setOnClickListener {
            navigate(workers[position])
        }

    }

    override fun getItemCount() = workers.size

    class ViewHolder(val binding: WorkerItemBinding) : RecyclerView.ViewHolder(binding.root)

}
