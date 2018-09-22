package com.example.sushiuivana.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sushiuivana.R
import com.example.sushiuivana.service.usecase.client.OrderCreation
import kotlinx.android.synthetic.main.activity_client_main.*

class ClientMainActivity : AppCompatActivity() {

    private val creationUseCase = OrderCreation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_main)
        title = "Создание заказа"
        generate_button.setOnClickListener {
            creationUseCase.generateOrder { basket ->
                order_text.text = basket.products.fold("") {
                    acc, product -> acc + "${product.name} - ${product.price} р.\n"
                }
            }
        }
        create_button.setOnClickListener {
            creationUseCase.createOrder { success ->
                if (success) Toast.makeText(this, "Заказ оформлен", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
