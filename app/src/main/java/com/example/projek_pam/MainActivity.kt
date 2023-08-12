package com.example.projek_pam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.projek_pam.activity.Daftar2Activity
import com.example.projek_pam.activity.MasukActivity
import com.example.projek_pam.fragment.AkunFragment
import com.example.projek_pam.fragment.HomeFragment
import com.example.projek_pam.fragment.PendaftaranFragment
import com.example.projek_pam.helper.sharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentHome : Fragment = HomeFragment()
    private val fragmentAkun : Fragment = AkunFragment()
    private val fragmentPendaftaran : Fragment = PendaftaranFragment()
    private val activityPendaftaran : Activity = Daftar2Activity()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var statusLogin = false

    private lateinit var s: sharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = sharedPref(this)

        setUpBottonNav()
    }
    fun setUpBottonNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()
        fm.beginTransaction().add(R.id.container, fragmentPendaftaran).hide(fragmentPendaftaran).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home->{
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_pendaftaran->{
                    callFragment(1, fragmentPendaftaran)
                }
                R.id.navigation_akun->{
                    if (s.getStatusLogin()){
                        callFragment(2, fragmentAkun)
                    }else{
                        startActivity(Intent(this, MasukActivity::class.java))
                    }
                }
            }
            false
        }
    }

    fun callFragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}