package com.example.projek_pam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.projek_pam.R
import com.example.projek_pam.adapter.adapterBerita
import com.example.projek_pam.app.ApiConfig
import com.example.projek_pam.model.Produk
import com.example.projek_pam.model.ResponModel
import com.inyongtisto.tutorial.adapter.AdapterSlider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvBerita: RecyclerView
    lateinit var rvKegiatan: RecyclerView
    lateinit var rvPengurus: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        init(view)
        getProduk()

        return view
    }

    private var listProduk:ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                if (res.success == 1){
                    listProduk = res.produks
                    displayProduk()
                }
            }
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

        })
    }

    fun displayProduk(){
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.gambar1)
        arrSlider.add(R.drawable.gambar2)
//        arrSlider.add(R.drawable.gambar3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

//        val layoutManager3 = LinearLayoutManager(activity)
//        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvBerita.adapter = adapterBerita(listProduk)
        rvBerita.layoutManager = layoutManager

        rvKegiatan.adapter = adapterBerita(listProduk)
        rvKegiatan.layoutManager = layoutManager2

//        rvPengurus.adapter = adapterBerita(listProduk)
//        rvPengurus.layoutManager = layoutManager3
    }

    fun init(view: View){
        vpSlider = view.findViewById(R.id.vp_slider)
        rvBerita = view.findViewById(R.id.rv_produk)
        rvKegiatan = view.findViewById(R.id.rv_jadwal)
//        rvPengurus = view.findViewById(R.id.rv_pengurus)
    }

//    val arrProduk: ArrayList<Berita>get(){
//        val arr = ArrayList<Berita>()
//        val p1 = Berita()
//        p1.judul = "Paskah"
//        p1.deskripsi = "kegiatan paskah di gereja HKBP Palmarum Tarutung"
//        p1.gambar = R.drawable.gambar1
//
//        val p2 = Berita()
//        p2.judul = "Pengeluaran"
//        p2.deskripsi = "Total Pengeluaran Minggu ini"
//        p2.gambar = R.drawable.gambar2
//
//        val p3 = Berita()
//        p3.judul = "Pemasukan"
//        p3.deskripsi = "Total Pemasukan Minggu ini"
//        p3.gambar = R.drawable.gambar3
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//
//        return arr
//    }
//
//    val arrKegiatan: ArrayList<Berita>get(){
//        val arr = ArrayList<Berita>()
//        val p1 = Berita()
//        p1.judul = "Paskah"
//        p1.deskripsi = "kegiatan paskah di gereja HKBP Palmarum Tarutung"
//        p1.gambar = R.drawable.gambar1
//
//        val p2 = Berita()
//        p2.judul = "Pengeluaran"
//        p2.deskripsi = "Total Pengeluaran Minggu ini"
//        p2.gambar = R.drawable.gambar2
//
//        val p3 = Berita()
//        p3.judul = "Pemasukan"
//        p3.deskripsi = "Total Pemasukan Minggu ini"
//        p3.gambar = R.drawable.gambar3
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//
//        return arr
//    }
//
//    val arrPengurus: ArrayList<Berita>get(){
//        val arr = ArrayList<Berita>()
//        val p1 = Berita()
//        p1.judul = "Paskah"
//        p1.deskripsi = "kegiatan paskah di gereja HKBP Palmarum Tarutung"
//        p1.gambar = R.drawable.gambar1
//
//        val p2 = Berita()
//        p2.judul = "Pengeluaran"
//        p2.deskripsi = "Total Pengeluaran Minggu ini"
//        p2.gambar = R.drawable.gambar2
//
//        val p3 = Berita()
//        p3.judul = "Pemasukan"
//        p3.deskripsi = "Total Pemasukan Minggu ini"
//        p3.gambar = R.drawable.gambar3
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//
//        return arr
//    }
}