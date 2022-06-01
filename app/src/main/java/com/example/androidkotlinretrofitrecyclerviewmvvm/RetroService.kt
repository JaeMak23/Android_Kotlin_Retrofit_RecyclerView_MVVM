package com.example.androidkotlinretrofitrecyclerviewmvvm

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    //https://gorest.co.in/public/v2/users
    @GET("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json"
    )
    fun getUsersList(): Call<UserList>

    //https://gorest.co.in/public/v2/users?name=a
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>

    //https://gorest.co.in/public/v2/users/121
    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<UserList>

    @POST("users")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun createUser(@Body param: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun updateUser(
        @Path("user_id") user_id: String,
        @Body params: User
    ): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>


}
