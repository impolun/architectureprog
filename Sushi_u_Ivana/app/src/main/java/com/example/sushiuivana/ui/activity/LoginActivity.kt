package com.example.sushiuivana.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sushiuivana.R
import com.example.sushiuivana.domain.entity.base.UserEntity
import com.example.sushiuivana.domain.entity.implementation.Client
import com.example.sushiuivana.domain.entity.implementation.Courier
import com.example.sushiuivana.domain.entity.implementation.Operator
import com.example.sushiuivana.service.usecase.Authorization
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var tag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tag = intent.getStringExtra("tag") ?: ""
        button.setOnClickListener {
            val user = when (tag) {
                "operator" -> Authorization().loginOperator(
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
                "courier" -> Authorization().loginCourier(
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
                "client" -> Authorization().loginClient(
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
                else -> null
            }
            if (user != null) startMainActivity(user)
            else Toast.makeText(this, "NOT FOUND", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startMainActivity(user: UserEntity) {
        when (user) {
            is Client -> startActivity(Intent(this, ClientMainActivity::class.java))
            is Operator -> startActivity(Intent(this, OperatorMainActivity::class.java))
            is Courier -> startActivity(Intent(this,  CourierMainActivity::class.java).putExtra("courier_id", user.id))
            else -> Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        }
    }
}
