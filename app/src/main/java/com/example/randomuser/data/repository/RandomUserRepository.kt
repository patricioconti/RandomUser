package com.example.randomuser.data.repository

import com.example.randomuser.data.network.RandomUserApi


/**
 * Repository prepared to add a local database in the future
 */

class RandomUserRepository() {

    //Call RandomUserApi object and function
    suspend fun getUser() = RandomUserApi.retrofitService.fetchRandomUser()


}