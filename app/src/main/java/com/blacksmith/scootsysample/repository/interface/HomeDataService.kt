package com.blacksmith.scootsysample.repository.`interface`

import com.blacksmith.scootsysample.data.model.Header
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeDataService {
    @GET("foodhall.homescreen.exam.json")
    fun getHomeData(): Observable<Header>
    @GET("banner.exam.json")
    fun getHomeBannerData(): Observable<Header>

    companion object {
        fun create(): HomeDataService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://betaapi.scootsy.com/")
                .build()

            return retrofit.create(HomeDataService::class.java)
        }
    }
}