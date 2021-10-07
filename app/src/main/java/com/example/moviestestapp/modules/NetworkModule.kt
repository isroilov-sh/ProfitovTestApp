package com.example.moviestestapp.modules

import com.example.moviestestapp.api.MainRepository
import com.example.moviestestapp.api.MediaApi
import com.example.moviestestapp.utils.URLConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModule {

    fun getAPIRepositoryModule(): Module {
        return module {
            single {
                MainRepository(get())
            }
        }
    }

    fun getMediaServiceModule(): Module {
        return module {
            single<MediaApi> {
                Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(get())
                    .baseUrl(URLConstants.BASE_URL)
                    .build().create(MediaApi::class.java)
            }
        }
    }

    fun getOKHttpClientModule(): Module {
        return module {
            single {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            }
        }
    }
}