package com.iruda.ecomm.data.auth.repositories

import android.content.Context
import android.util.Log
import com.iruda.ecomm.data.auth.database.userProtoDataStore
import com.iruda.ecomm.data.auth.mappers.AuthMapper
import com.iruda.ecomm.data.auth.models.AuthRequestModel
import com.iruda.ecomm.data.auth.models.AuthResponseModel
import com.iruda.ecomm.data.auth.network.AuthApiFactory
import com.iruda.ecomm.domain.auth.entities.AuthResponse
import com.iruda.ecomm.domain.auth.repositories.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class AuthRepositoryImpl(
    private val factory: AuthApiFactory,
    private val mapper: AuthMapper,
    private val context: Context
) : AuthRepository {

    override fun getAuthResponse(): Flow<AuthResponse> {
        val proto = context.userProtoDataStore.data
        return proto.map { userProto ->
            mapper.mapProtoModelToEntity(userProto)
        }
    }

    override suspend fun authorizeWithEmail(email: String, password: String) {
        factory.apiService.loginWithEmail(
            AuthRequestModel(
                userName = email,
                password = password
            )
        ).enqueue(object : retrofit2.Callback<AuthResponseModel> {
            override fun onResponse(
                call: Call<AuthResponseModel>,
                response: Response<AuthResponseModel>
            ) {
                val authResponse = response.body()
                // TODO: Remove Log AuthError
                Log.d("AuthError", "Code: " + response.code().toString())
                Log.d("AuthError", "Message: " + response.message())
                Log.d("AuthError", "Body: " + response.body().toString())
                Log.d("AuthError", "Error Body: " + response.errorBody())

                if (response.isSuccessful && authResponse != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        context.userProtoDataStore.updateData { currentUserProto ->
                            currentUserProto.toBuilder()
                                .setId(authResponse.id)
                                .setToken(authResponse.token)
                                .setIsAuthorized(true)
                                .build()
                        }
                    }

                }

            }

            override fun onFailure(call: Call<AuthResponseModel>, t: Throwable) {
                Log.d("AuthError", call.toString())
                Log.d("AuthError", t.toString())
            }
        }
        )
    }
}