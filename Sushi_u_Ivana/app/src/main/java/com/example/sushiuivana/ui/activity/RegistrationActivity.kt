package com.example.sushiuivana.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sushiuivana.R
import com.example.sushiuivana.service.usecase.Authorization
import kotlinx.android.synthetic.main.activity_registartion.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var tag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registartion)
        tag = intent.getStringExtra("tag") ?: ""
        button.setOnClickListener {
            when (tag) {
                "operator" -> Authorization().registerOperator(
                        name = name_edit.text.toString(),
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
                "courier" -> Authorization().registerCourier(
                        name = name_edit.text.toString(),
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
                "client" -> Authorization().registerClient(
                        name = name_edit.text.toString(),
                        login = login_edit.text.toString(),
                        password = password_edit.text.toString()
                )
            }
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }
}
