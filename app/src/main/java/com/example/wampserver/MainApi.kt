package com.example.wampserver

import retrofit2.http.GET

interface MainApi {
    @GET("get_all_items")
    suspend fun getAllUsers(): List<User>
}