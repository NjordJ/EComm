package com.iruda.ecomm.data.auth.mappers

import com.iruda.ecomm.data.auth.models.AuthModel
import com.iruda.ecomm.domain.auth.entities.Auth

class AuthMapper {

    fun mapModelToEntity(model: AuthModel) = Auth(
        token = model.token
    )
}