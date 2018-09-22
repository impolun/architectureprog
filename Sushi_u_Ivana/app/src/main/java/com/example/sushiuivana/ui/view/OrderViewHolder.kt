package com.example.sushiuivana.ui.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.sushiuivana.domain.entity.implementation.Order
import kotlinx.android.synthetic.main.item_order.view.*

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(order: Order) {
        itemView.product_list.removeAllViewsInLayout()
        order.products.forEach {
            itemView.product_list.addView(TextView(itemView.context).apply { text = "${it.name} - ${it.price} р." })
        }
        itemView.status_text.text = order.status.str
    }
}