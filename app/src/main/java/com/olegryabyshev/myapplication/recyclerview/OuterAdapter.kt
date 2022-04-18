package com.olegryabyshev.myapplication.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olegryabyshev.myapplication.R
import com.olegryabyshev.myapplication.data.OuterClass

class OuterAdapter : ListAdapter<OuterClass, OuterAdapter.OuterViewHolder>(OuterDiffUtil) {

    class OuterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTV: TextView = view.findViewById(R.id.outer_id_tv)
        val innerRecyclerView: RecyclerView = view.findViewById(R.id.inner_recycler_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): OuterViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.outer_item, viewGroup, false)
        return OuterViewHolder(view)
    }

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        val model: OuterClass = getItem(position)

        val isExpandable: Boolean = model.isOpen

        holder.idTV.text = model.textId
        holder.innerRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)

        val adapter = InnerAdapter()
        holder.innerRecyclerView.adapter = adapter

        if (model.isOpen) {
            adapter.submitList(model.array)
        }

        holder.itemView.setOnClickListener {
            model.isOpen = !model.isOpen

            if (model.isOpen){
                adapter.submitList(model.array)
            } else {
                adapter.submitList(emptyList())
            }

            notifyItemChanged(holder.adapterPosition)
        }
    }

    private object OuterDiffUtil : DiffUtil.ItemCallback<OuterClass>() {
        override fun areItemsTheSame(oldItem: OuterClass, newItem: OuterClass): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OuterClass, newItem: OuterClass): Boolean {
            return oldItem == newItem
        }
    }
}