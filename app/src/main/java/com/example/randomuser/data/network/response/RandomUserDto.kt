package com.example.randomuser.data.network.response

import com.example.randomuser.domain.RandomUser

/**
 * This data class defines the whole response
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 * (See nested classes)
 */


data class RandomUserDto(
    val info: Info,
    val results: List<Result>
)

/**
 * Convert Network results to domain objects
 */
fun RandomUserDto.asDomainModel(): List<RandomUser> {
    return results.map {
        RandomUser(
            photo = it.picture.large,
            name = "${it.name.first} ${it.name.last}",
            email = it.email,
            //Truncate birthday when finding a T
            birthday = it.dob.date.substringBeforeLast(
                delimiter = 'T',
                missingDelimiterValue = "T not found"
            ),
            age = it.dob.age,
            address = "${it.location.street.number} ${it.location.street.name}",
            phone = it.phone,
            password = it.login.password
        )
    }

}

