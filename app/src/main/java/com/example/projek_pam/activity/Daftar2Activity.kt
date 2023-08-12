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

class Daftar2Activity : AppCompatActivity() {

    lateinit var s: sharedPref
    lateinit var button: Button
    lateinit var text_nama: EditText
    lateinit var text_ayah: EditText
    lateinit var text_ibu: EditText
    lateinit var text_alamat: EditText
    lateinit var text_tanggal: EditText
    lateinit var text_gereja: EditText
    lateinit var pb: ProgressBar
    companion object{
        const val nama = "nama"
        const val nama_ayah = "nama_ayah"
        const val nama_ibu = "nama_ibu"
        const val alamat = "alamat"
        const val tanggal = "tanggal"
        const val gereja = "gereja"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar2)

        button = findViewById(R.id.btn_daftar)
        text_nama = findViewById(R.id.edit_nama)
        text_ayah = findViewById(R.id.edit_nama_ayah)
        text_ibu = findViewById(R.id.edit_nama_ibu)
        text_alamat = findViewById(R.id.edit_alamat)
        text_tanggal = findViewById(R.id.edit_tanggal)
        text_gereja = findViewById(R.id.edit_asal_gereja)
        pb = findViewById(R.id.pb)

        s = sharedPref(this)

        button.setOnClickListener {
            if(s.getStatusLogin()) {
                daftar()
            }else {
                val intent = Intent(this, MasukActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun daftar(){
        if(text_nama.text.isEmpty()){
            text_nama.error = "Kolom nama tidak boleh kosong"
            text_nama.requestFocus()
            return
        }else if(text_ayah.text.isEmpty()) {
            text_ayah.error = "Kolom nama ayah tidak boleh kosong"
            text_ayah.requestFocus()
            return
        }else if(text_ibu.text.isEmpty()) {
            text_ibu.error = "Kolom nama ibu tidak boleh kosong"
            text_ibu.requestFocus()
            return
        }else if(text_alamat.text.isEmpty()) {
            text_alamat.error = "Kolom alamat tidak boleh kosong"
            text_alamat.requestFocus()
            return
        }else if(text_tanggal.text.isEmpty()) {
            text_tanggal.error = "Kolom tanggal tidak boleh kosong"
            text_tanggal.requestFocus()
            return
        }else if(text_gereja.text.isEmpty()) {
            text_gereja.error = "Kolom asal gereja tidak boleh kosong"
            text_gereja.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.daftar(text_nama.text.toString(), text_ayah.text.toString(), text_ibu.text.toString(), text_alamat.text.toString(), text_tanggal.text.toString(), text_gereja.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                if(response.isSuccessful) {
                    val respons = response.body()!!

                    if (respons.success == 1){
//                        s.setPendaftaran(respons.pendaftaran)
                        val intent = Intent(this@Daftar2Activity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        intent.putExtra(nama, s.getPendaftaran()!!.nama)
//                        intent.putExtra(nama_ayah, s.getPendaftaran()!!.nama_ayah)
//                        intent.putExtra(nama_ibu, s.getPendaftaran()!!.nama_ibu)
//                        intent.putExtra(alamat, s.getPendaftaran()!!.alamat)
//                        intent.putExtra(tanggal, s.getPendaftaran()!!.tanggal)
//                        intent.putExtra(gereja, s.getPendaftaran()!!.gereja)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@Daftar2Activity, "Selamat Pendaftaran Anda Sukses "+s.getUser()?.name, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@Daftar2Activity, "Error:"+respons.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@Daftar2Activity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}