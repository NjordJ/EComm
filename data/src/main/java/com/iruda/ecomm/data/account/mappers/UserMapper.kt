package com.iruda.ecomm.data.account.mappers

import com.iruda.ecomm.data.account.models.AddressModel
import com.iruda.ecomm.data.account.models.GeoLocationModel
import com.iruda.ecomm.data.account.models.NameModel
import com.iruda.ecomm.data.account.models.UserModel
import com.iruda.ecomm.domain.account.entities.User

class UserMapper {

    fun mapModelToEntity(model: UserModel) = User(
        id = model.id,
        email = model.email,
        userName = model.userName,
        password = model.password,
        name = mapModelNameToEntityName(model = model.name),
        phone = model.phone,
        address = mapModelAddressToEntityAddress(model = model.address)
    )

    private fun mapModelNameToEntityName(model: NameModel) = User.Name(
        firstName = model.firstName,
        lastName = model.lastName
    )

    private fun mapModelAddressToEntityAddress(model: AddressModel) = User.Address(
        city = model.city,
        street = model.street,
        number = model.number,
        zipcode = model.zipcode,
        geolocation = mapModelGeoLocationToEntityGeoLocation(model = model.geolocation)
    )

    private fun mapModelGeoLocationToEntityGeoLocation(model: GeoLocationModel) =
        User.Address.GeoLocation(
            lat = model.lat,
            long = model.long
        )
}