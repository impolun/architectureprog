package com.example.sushiuivana.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sushiuivana.R
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.ui.view.OrderViewHolder

class OrderAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<OrderViewHolder>() {
    val items = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener.onItemClick(items[position]) }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Order)
    }
}