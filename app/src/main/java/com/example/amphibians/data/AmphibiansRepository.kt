package com.example.amphibians.data

import com.example.amphibians.model.AmphibianModelClass
import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AmphibiansRepository {
    suspend fun getAmphibiansData(): List<AmphibianModelClass>
}

class NetworkAmphibiansRepository(private val retrofitService: AmphibiansApiService):AmphibiansRepository{
    override suspend fun getAmphibiansData(): List<AmphibianModelClass> {
        return retrofitService.getAmphibiansData()
    }

}