package com.example.amphibians.data

import com.example.amphibians.model.AmphibianModelClass
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibiansData(): List<AmphibianModelClass>
}

    private val baseUrl ="https://android-kotlin-fun-mars-server.appspot.com/"

    val retrofit:Retrofit = Retrofit.Builder().addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).baseUrl(baseUrl).build()


object Api {
    val retrofitService : AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
}
