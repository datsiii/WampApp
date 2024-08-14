package com.example.wampserver

import com.example.wampserver.data.ImageData
import com.example.wampserver.data.ImageUploadResponse
import com.example.wampserver.data.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {
    @GET("get_all_items.php")
    suspend fun getAllUsers(): List<User>

    @POST("save_user.php")
    suspend fun saveUser(@Body user: User)

    @POST("upload_image.php")
    suspend fun uploadImage(@Body imageData: ImageData): ImageUploadResponse
}