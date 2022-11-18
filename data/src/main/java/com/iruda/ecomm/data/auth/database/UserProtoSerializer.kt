package com.iruda.ecomm.data.auth.database

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.jetpackdatasource.UserProto
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

private const val DATA_STORE_FILE_NAME = "userproto.pb"

object UserProtoSerializer : Serializer<UserProto> {
    override val defaultValue: UserProto = UserProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserProto {
        try {
            return UserProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserProto,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.userProtoDataStore: DataStore<UserProto> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserProtoSerializer
)
