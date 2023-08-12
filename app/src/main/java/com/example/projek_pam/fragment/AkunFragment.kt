package com.example.projek_pam.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projek_pam.MainActivity
import com.example.projek_pam.R
import com.example.projek_pam.activity.LoginActivity
import com.example.projek_pam.activity.RegisterActivity
import com.example.projek_pam.helper.sharedPref

/**
 * A simple [Fragment] subclass.
 * Use the [AkunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AkunFragment : Fragment() {

    lateinit var s:sharedPref
    lateinit var btnLogout:TextView
    lateinit var tvNama:TextView
    lateinit var tvEmail: TextView
    lateinit var tvPhone:TextView
    lateinit var tvAlamat:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        init(view)

        val intent = activity?.intent

        tvNama.text = intent?.getStringExtra(RegisterActivity.name)
        tvEmail.text = intent?.getStringExtra(RegisterActivity.email)
        tvPhone.text = intent?.getStringExtra(RegisterActivity.phone)
        tvAlamat.text = intent?.getStringExtra(RegisterActivity.alamat)

        s = sharedPref(requireActivity())

        btnLogout.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            s.setStatusLogin(false)
        }

        setData()
        return view
    }

    fun setData(){

        if(s.getUser() == null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        val user = s.getUser()!!

//        tvNama.text = user.name
//        tvEmail.text = user.email
//        tvPhone.text = user.phone
//        tvAlamat.text = user.alamat
    }

    private fun init(view: View){
        btnLogout = view.findViewById(R.id.btn_logout)
        tvNama = view.findViewById(R.id.tv_nama)
        tvAlamat = view.findViewById(R.id.tv_alamat)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_telepon)
    }
}