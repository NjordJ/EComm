package com.iruda.ecomm.domain.account.entities

data class User(
    val id: Int,
    val email: String,
    val userName: String,
    val password: String,
    val name: Name,
    val phone: String,
    val address: Address
) {

    data class Name(
        val firstName: String,
        val lastName: String
    )

    data class Address(
        val city: String,
        val street: String,
        val number: Int,
        val zipcode: String,
        val geolocation: GeoLocation
    ) {

        data class GeoLocation(
            val lat: String,
            val long: String
        )
    }
}

