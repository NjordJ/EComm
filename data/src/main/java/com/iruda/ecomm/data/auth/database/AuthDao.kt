package com.iruda.ecomm.data.auth.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iruda.ecomm.data.auth.models.AuthResponseModel

@Dao
interface AuthDao {

    @Query("SELECT * FROM auth_response_table WHERE id == :id LIMIT 1")
    fun getUserInfo(id: Int): LiveData<AuthResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: AuthResponseModel)
}