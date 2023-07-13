package com.rahmat.palindrome.data.remote.retrofit

import com.rahmat.palindrome.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/users")
    suspend fun getUserList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): UserResponse
}