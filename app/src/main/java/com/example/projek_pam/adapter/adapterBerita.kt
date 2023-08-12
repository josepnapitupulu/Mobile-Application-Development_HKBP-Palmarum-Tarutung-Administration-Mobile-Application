package com.example.projek_pam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projek_pam.R
import com.example.projek_pam.model.Produk
import com.squareup.picasso.Picasso

class adapterBerita(var data: ArrayList<Produk>) : RecyclerView.Adapter<adapterBerita.Holder>(){

    class Holder(view: View):RecyclerView.ViewHolder(view){
        val tvJudul = view.findViewById<TextView>(R.id.tv_judul)
        val tvJabatan = view.findViewById<TextView>(R.id.tv_jabatan)
        val tvDeskripsi = view.findViewById<TextView>(R.id.tv_detail)
        val imgBerita = view.findViewById<ImageView>(R.id.img_berita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvJudul.text = data[position].nama
        holder.tvJabatan.text = data[position].jabatan
        holder.tvDeskripsi.text = data[position].deskripsi
//        holder.imgBerita.setImageResource(data[position].foto)

        val image= "http://192.168.229.27:8080/admin_PAM/public/storage/produk/"+data[position].foto
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.hkbp)
            .error(R.drawable.hkbp)
            .into(holder.imgBerita)
    }

}