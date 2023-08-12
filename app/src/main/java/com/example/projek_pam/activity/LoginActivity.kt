package com.example.projek_pam.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projek_pam.MainActivity
import com.example.projek_pam.R
import com.example.projek_pam.app.ApiConfig
import com.example.projek_pam.helper.sharedPref
import com.example.projek_pam.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var s:sharedPref
    lateinit var button: Button
    lateinit var text_email: EditText
    lateinit var text_password: EditText
    lateinit var pb: ProgressBar
    companion object{
        const val name = "name"
        const val email = "email"
        const val phone = "phone"
        const val alamat = "alamat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.btn_login)
        text_email = findViewById(R.id.edit_email)
        text_password = findViewById(R.id.edit_password)
        pb = findViewById(R.id.pb)

        s = sharedPref(this)

        button.setOnClickListener {
            login()
        }
    }
    fun login(){
        if(text_email.text.isEmpty()) {
            text_email.error = "Kolom email tidak boleh kosong"
            text_email.requestFocus()
            return
        }else if(text_password.text.isEmpty()) {
            text_password.error = "Kolom password tidak boleh kosong"
            text_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(text_email.text.toString(), text_password.text.toString()).enqueue(object :
            Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respons = response.body()!!

                if (respons.success == 1){
                    s.setStatusLogin(true)
                    s.setUser(respons.user)
//                    s.setString(s.nama, respons.user.name)
//                    s.setString(s.phone, respons.user.phone)
//                    s.setString(s.email, respons.user.email)
//                    s.setString(s.alamat, respons.user.alamat)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra(name, s.getUser()!!.name)
                    intent.putExtra(email, s.getUser()!!.email)
                    intent.putExtra(phone, s.getUser()!!.phone)
                    intent.putExtra(alamat, s.getUser()!!.alamat)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Selamat datang "+respons.user.name, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@LoginActivity, "Error:"+respons.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}