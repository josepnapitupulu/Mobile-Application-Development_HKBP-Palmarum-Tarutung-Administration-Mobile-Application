package com.example.projek_pam.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.projek_pam.R
import com.example.projek_pam.helper.sharedPref


class MasukActivity : AppCompatActivity() {

    lateinit var s:sharedPref
    lateinit var button: Button
    lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        s = sharedPref(this)

        mainButton()
    }

    private fun mainButton(){
        button = findViewById(R.id.btn_prosesLogin)
        button2 = findViewById(R.id.btn_register)

        button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}