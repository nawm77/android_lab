package com.nawm.android_labs.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.*
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import com.nawm.android_labs.domain.Character
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


interface RetrofitNetworkApi {
    @GET(value = "characters")
    suspend fun getCharacters(): List<Character>

    @GET(value = "characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character?
}

private const val NETWORK_BASE_URL = "https://www.anapioficeandfire.com/api/"

class RetrofitNetwork : RetrofitNetworkApi {
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val networkApi: RetrofitNetworkApi by lazy {
        Retrofit.Builder()
            .baseUrl(NETWORK_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(get("application/json"))
            )
            .build()
            .create(RetrofitNetworkApi::class.java)
    }

    override suspend fun getCharacters(): List<Character> = networkApi.getCharacters()

    override suspend fun getCharacterById(id: Int): Character? = networkApi.getCharacterById(id)

    suspend fun getCharactersInRange(startId: Int, endId: Int): List<Character> = coroutineScope {
        (startId..endId).map { id ->
            async {
                networkApi.getCharacterById(id)
            }
        }.awaitAll().filterNotNull()
    }
}