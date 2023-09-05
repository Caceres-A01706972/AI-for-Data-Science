package com.example.lab5mod4.data.network

import android.net.sip.SipErrorCode.TIME_OUT
import android.util.Log
import com.example.lab5mod4.secret.TmdbApiKeys
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json

class TmdbHttpClient {
    fun getHttpClient() = HttpClient(Android){
        install(JsonFeature){
            serializer = KotlinxSerializer(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
        install(Logging){
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i(TAG_KTOR_LOGGER,message)
                }
            }
        }
        install(ResponseObserver){
            onResponse {response->
                Log.i(TAG_HTTP_STATUS_LOGGER,"${response.status.value}")
            }
        }
        install(DefaultRequest){
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${TmdbApiKeys.API_KEY}")
        }
    }
    companion object{
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }
}