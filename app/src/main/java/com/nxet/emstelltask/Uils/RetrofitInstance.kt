package com.nxet.emstelltask.Uils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : DataApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://173.255.225.103")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(DataApi::class.java)
    }
}