package com.olegryabyshev.myapplication.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olegryabyshev.myapplication.R
import com.olegryabyshev.myapplication.data.InnerClass

class InnerAdapter : ListAdapter<InnerClass, InnerAdapter.InnerViewHolder>(OuterDiffUtil) {

    class InnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTV: TextView = view.findViewById(R.id.outer_id_tv)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InnerViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.inner_item, viewGroup, false)
        return InnerViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: InnerViewHolder, position: Int) {
        viewHolder.idTV.text = currentList[position].innerId
    }

    private object OuterDiffUtil : DiffUtil.ItemCallback<InnerClass>() {
        override fun areItemsTheSame(oldItem: InnerClass, newItem: InnerClass): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: InnerClass, newItem: InnerClass): Boolean {
            return oldItem == newItem
        }
    }
}