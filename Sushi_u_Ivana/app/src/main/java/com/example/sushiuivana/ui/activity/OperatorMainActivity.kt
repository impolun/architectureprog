package com.example.sushiuivana.ui.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.sushiuivana.R
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.entity.implementation.OrderStatus
import com.example.sushiuivana.service.usecase.operator.OrderManagment
import com.example.sushiuivana.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.activity_operator_main.*


class OperatorMainActivity : AppCompatActivity(), OrderAdapter.OnItemClickListener {
    override fun onItemClick(item: Order) {
        showStatusSelectionDialog { newStatus ->
            OrderManagment().changeOrderStatus(item, newStatus)
            updateOrders()
        }
    }

    private val adapter = OrderAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_main)
        title = "Список заказов"
        recycler.adapter = adapter
        updateOrders()
    }

    private fun updateOrders() {
        OrderManagment().getAllOrders { orders ->
            adapter.items.clear()
            adapter.items.addAll(orders)
            adapter.notifyDataSetChanged()
        }
    }

    private fun showStatusSelectionDialog(block: (newStatus: OrderStatus) -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Изменение статуса заказа")

        builder.setItems(OrderStatus.values().map(OrderStatus::str).toTypedArray()) { _, which ->
            block(OrderStatus.values()[which])
        }

        val dialog = builder.create()
        dialog.show()
    }
}
