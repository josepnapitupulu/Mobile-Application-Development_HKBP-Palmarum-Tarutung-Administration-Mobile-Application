package com.example.projek_pam.app

import com.example.projek_pam.model.ResponModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("email") emailJ : String,
        @Field("phone") phoneJ : String,
        @Field("alamat") alamatJ : String,
        @Field("password") passwordJ : String
    ):Call<ResponModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") emailJ : String,
        @Field("password") passwordJ : String
    ):Call<ResponModel>

    @GET("produk")
    fun getProduk():Call<ResponModel>

    @FormUrlEncoded
    @POST("pendaftaran")
    fun daftar(
        @Field("nama") nama : String,
        @Field("nama_ayah") nama_ayahJ : String,
        @Field("nama_ibu") nama_ibuJ : String,
        @Field("alamat") alamatJ : String,
        @Field("tanggal") tanggal : String,
        @Field("gereja") gerejaJ : String
    ):Call<ResponModel>
}