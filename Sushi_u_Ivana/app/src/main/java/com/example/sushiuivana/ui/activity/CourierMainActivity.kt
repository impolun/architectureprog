package com.example.sushiuivana.ui.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sushiuivana.R
import com.example.sushiuivana.domain.entity.implementation.Courier
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.entity.implementation.OrderStatus
import com.example.sushiuivana.domain.repository.implementation.CourierRepositoryImpl
import com.example.sushiuivana.service.usecase.courier.OrderDelivery
import com.example.sushiuivana.service.usecase.operator.OrderManagment
import com.example.sushiuivana.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.activity_operator_main.*


class CourierMainActivity : AppCompatActivity(), OrderAdapter.OnItemClickListener {
    override fun onItemClick(item: Order) {
        showStatusSelectionDialog(item.status) { newStatus ->
            OrderDelivery().changeOrderStatus(item, courier, newStatus)
            updateOrders()
        }
    }

    private val adapter = OrderAdapter(this)
    private var courier: Courier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courier_main)
        title = "Список заказов"
        val courierId = intent.getStringExtra("courier_id")
        courier = CourierRepositoryImpl().read(courierId)
        recycler.adapter = adapter
        updateOrders()
    }

    private fun updateOrders() {
        val courier = courier ?: return
        OrderDelivery().getCourierOrders(courier) { orders ->
            adapter.items.clear()
            adapter.items.addAll(orders)
            adapter.notifyDataSetChanged()
        }
    }

    private fun showStatusSelectionDialog(currentStatus: OrderStatus, block: (newStatus: OrderStatus) -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Изменение статуса заказа")

        val statuses = when (currentStatus) {
            OrderStatus.WAIT_DELIVERY -> arrayOf(OrderStatus.IN_DELIVERY)
            OrderStatus.IN_DELIVERY -> arrayOf(OrderStatus.COMPLETE)
            else -> {
                Toast.makeText(this, "Смена статуса недоступна", Toast.LENGTH_SHORT).show()
                return
            }
        }
        builder.setItems(statuses.map(OrderStatus::str).toTypedArray()) { _, which ->
            block(statuses[which])
        }

        val dialog = builder.create()
        dialog.show()
    }
}
