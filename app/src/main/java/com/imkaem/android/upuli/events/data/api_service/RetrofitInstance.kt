package com.imkaem.android.upuli.events.data.api_service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* TODO this will later be replaced by Network Module with Hilt*/
/*
* https://medium.com/@desiappdev24/fetching-data-using-retrofit-in-jetpack-compose-a-complete-guide-97f4c2101cb7
* https://medium.com/@andbyte18/clean-architecture-in-android-with-mvvm-hilt-retrofit-jetpack-compose-a27f8a817d20
* https://medium.com/@desiappdev24/fetching-data-using-retrofit-in-jetpack-compose-a-complete-guide-97f4c2101cb7
* */
object RetrofitInstance {
    /* this is a singleton instance */
    private const val BASE_URL = "https://u-puli-api.onrender.com/"

    val api: UPuliApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UPuliApiService::class.java)
    }
}