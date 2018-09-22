package com.example.sushiuivana.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sushiuivana.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        operator_register_button.setOnClickListener { startRegistrationActivity("operator") }
        courier_register_button.setOnClickListener { startRegistrationActivity("courier") }
        client_register_button.setOnClickListener { startRegistrationActivity("client") }

        operator_login_button.setOnClickListener { startLoginActivity("operator") }
        courier_login_button.setOnClickListener { startLoginActivity("courier") }
        client_login_button.setOnClickListener { startLoginActivity("client") }
    }

    private fun startRegistrationActivity(tag: String) {
        startActivity(Intent(this, RegistrationActivity::class.java).putExtra("tag", tag))
    }

    private fun startLoginActivity(tag: String) {
        startActivity(Intent(this, LoginActivity::class.java).putExtra("tag", tag))
    }
}
