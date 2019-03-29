package com.farmdrop.producers.ui.producer.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.farmdrop.producers.R
import com.farmdrop.producers.data.Producer
import com.farmdrop.producers.databinding.ProducerItemBinding

class ProducersAdapter : RecyclerView.Adapter<ProducersAdapter.ViewHolder>() {
    private lateinit var producers: List<Producer>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProducerItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.producer_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(producers[position])
    }

    override fun getItemCount(): Int {
        return if (::producers.isInitialized) producers.size else 0
    }

    fun updateProducers(producers: List<Producer>) {
        this.producers = producers
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ProducerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ProducerViewModel()

        fun bind(producer: Producer) {
            viewModel.bind(producer)
            binding.viewModel = viewModel
        }
    }
}