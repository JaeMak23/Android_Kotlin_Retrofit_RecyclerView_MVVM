package com.example.androidkotlinretrofitrecyclerviewmvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    val TAG="LOG"

    var recyclerListData: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserListObservable(): MutableLiveData<List<User>> {
        return recyclerListData
    }

    fun getUserList() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroService::class.java)
        val call = retrofitInstance.getUsersList()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    Log.d(TAG,"OnResponse =  Successful ")
                    recyclerListData.postValue(response.body())
                } else {
                    Log.d(TAG,"OnResponse = Failed ")
                    Log.d(TAG,"Error : ${response.message()}")
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d(TAG,"OnFailure called")
                Log.d("LOG","Response failed due to : ${t.message.toString()}")
                recyclerListData.postValue(null)
            }
        })
    }

    fun searchUser( searchText : String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroService::class.java)
        val call = retrofitInstance.searchUsers(searchText)
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}