package com.mic.spinnerproject.APi



import com.mic.spinnerproject.mode.township
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("setup/township")
    fun getTownship(): Call<township>

}