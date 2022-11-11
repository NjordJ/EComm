package com.iruda.ecomm.data.account.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey
    val id: Int,
    val email: String,
    val userName: String,
    val password: String,
    @Embedded val name: NameModel,
    val phone: String,
    @Embedded val address: AddressModel
)

@Entity(tableName = "user_name_table", primaryKeys = ["firstName", "lastName"])
data class NameModel(
    val firstName: String,
    val lastName: String
)

@Entity(tableName = "user_address_table", primaryKeys = ["city", "street", "number", "zipcode"])
data class AddressModel(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    @Embedded val geolocation: GeoLocationModel
)

@Entity(tableName = "user_geolocation_table", primaryKeys = ["lat", "long"])
data class GeoLocationModel(
    val lat: String,
    val long: String
)