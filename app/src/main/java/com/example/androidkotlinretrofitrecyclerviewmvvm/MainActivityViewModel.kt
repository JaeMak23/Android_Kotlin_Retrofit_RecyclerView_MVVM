package com.example.androidkotlinretrofitrecyclerviewmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<UserList> = MutableLiveData()

    fun getUserListObservable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUserList() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroService::class.java)
        val call = retrofitInstance.getUsersList()
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }

    fun searchUser( searchText : String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(RetroService::class.java)
        val call = retrofitInstance.searchUsers(searchText)
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}