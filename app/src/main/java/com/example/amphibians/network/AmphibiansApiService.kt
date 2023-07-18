package com.example.amphibians.network

import com.example.amphibians.model.AmphibianModelClass
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibiansData(): List<AmphibianModelClass>
}