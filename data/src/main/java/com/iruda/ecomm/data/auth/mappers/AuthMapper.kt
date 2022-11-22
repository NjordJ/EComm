package com.iruda.ecomm.data.auth.mappers

import com.example.jetpackdatasource.UserProto
import com.iruda.ecomm.domain.auth.entities.AuthResponse

class AuthMapper {

    fun mapProtoModelToEntity(proto: UserProto) = AuthResponse(
        id = proto.id,
        token = proto.token,
        isAuthorized = proto.isAuthorized
    )
}