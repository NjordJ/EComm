package com.iruda.ecomm.data.auth.mappers

import com.iruda.ecomm.data.auth.models.AuthModel
import com.iruda.ecomm.domain.auth.entities.AuthResponse

class AuthMapper {

    fun mapModelToEntity(model: AuthModel) = AuthResponse(
        token = model.token
    )
}