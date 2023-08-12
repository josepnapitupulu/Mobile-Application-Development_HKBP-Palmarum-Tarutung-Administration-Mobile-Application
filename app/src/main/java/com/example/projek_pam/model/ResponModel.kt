package com.example.projek_pam.model

class ResponModel {
    var success = 0
    lateinit var message:String
    var user = User()
    var pendaftaran = Pendaftaran()
    var produks:ArrayList<Produk> = ArrayList()
    var berita:ArrayList<Info> =ArrayList()
}