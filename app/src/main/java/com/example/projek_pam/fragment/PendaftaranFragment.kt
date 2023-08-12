package com.example.projek_pam.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.projek_pam.R
import com.example.projek_pam.activity.Daftar2Activity
import com.example.projek_pam.helper.sharedPref

/**
 * A simple [Fragment] subclass.
 * Use the [PendaftaranFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PendaftaranFragment : Fragment() {

    lateinit var s: sharedPref
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pendaftaran, container, false)
        val view: View = inflater.inflate(R.layout.fragment_pendaftaran, container, false)
        button = view.findViewById(R.id.btn_daftar_sidi)
        button.setOnClickListener {
            redirectToActivity()
        }



        return view
    }
    private fun redirectToActivity() {
        val intent = Intent(activity, Daftar2Activity::class.java)
        startActivity(intent)
    }
}