package com.iruda.ecomm.data.auth.mappers

import com.iruda.ecomm.data.auth.models.AuthResponseModel
import com.iruda.ecomm.domain.auth.entities.AuthResponse

class AuthMapper {

    fun mapModelToEntity(model: AuthResponseModel) = AuthResponse(
        id = model.id,
        userName = model.userName,
        email = model.userName,
        firstName = model.firstName,
        lastName = model.lastName,
        image = model.image,
        token = model.token
    )
}