package com.example.randomuser.data.network

import com.example.randomuser.data.network.response.RandomUserDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//Base URL for web service
private const val BASE_URL =
    "https://randomuser.me"

//Create Moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Compiler for creating and compiling Retrofit object
//With this Retrofit can get a JSON response and show it as a string
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RandomUserApiService {

    //Indicate retrofit that is a GET request and that the extreme is /api
    @GET("api")

    //To fetch the RandomUser
    suspend fun fetchRandomUser(): RandomUserDto

}

//Expose the service to the rest of the app using object declaration.
object RandomUserApi {

    val retrofitService: RandomUserApiService by lazy {
        retrofit.create(RandomUserApiService::class.java)
    }

}