package com.example.randomuser.data.network.response

data class Id(
    val name: String,
    //I added can hold null because  id.value is sometimes null at .json
    val value: String?
)