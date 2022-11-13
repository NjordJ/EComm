package com.iruda.ecomm.data.auth.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.iruda.ecomm.data.auth.database.AuthDao
import com.iruda.ecomm.data.auth.mappers.AuthMapper
import com.iruda.ecomm.data.auth.models.AuthRequestModel
import com.iruda.ecomm.data.auth.models.AuthResponseModel
import com.iruda.ecomm.data.auth.network.AuthApiFactory
import com.iruda.ecomm.domain.auth.entities.AuthResponse
import com.iruda.ecomm.domain.auth.repositories.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class AuthRepositoryImpl(
    private val authDao: AuthDao,
    private val factory: AuthApiFactory,
    private val mapper: AuthMapper
) : AuthRepository {

    override fun getAuthResponse(id: Int): LiveData<AuthResponse> {
        return Transformations.map(authDao.getUserInfo(id = id)) {
            mapper.mapModelToEntity(it)
        }
    }

    override suspend fun authorizeWithEmail() {
        factory.apiService.loginWithEmail(
            AuthRequestModel(
                userName = "atuny0",
                password = "9uQFF1Lh"
            )
        ).enqueue(object : retrofit2.Callback<AuthResponseModel> {
            override fun onResponse(
                call: Call<AuthResponseModel>,
                response: Response<AuthResponseModel>
            ) {
                val authResponse = response.body()

                if (response.isSuccessful && authResponse != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        authDao.insertUserInfo(authResponse)
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