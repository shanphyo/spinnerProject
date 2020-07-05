package com.mic.spinnerproject.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.spinnerproject.APi.TownshipApi
import com.mic.spinnerproject.mode.township
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TownShipViewModel : ViewModel() {

    private var township: MutableLiveData<township> = MutableLiveData()

    fun getTownship(): LiveData<township> = township
    val townshipApi: TownshipApi = TownshipApi()

    fun readTownship() {



        val call = townshipApi.getTownships()

        call.enqueue(object : Callback<township> {
            override fun onFailure(call: Call<township>, t: Throwable) {
                Log.d("Result Error>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<township>, response: Response<township>) {
                response?.isSuccessful.let {
                    val townshipResult = township(response.body()?.townships?: emptyList())
                    township.value = townshipResult


                }
            }

        })
    }
}
