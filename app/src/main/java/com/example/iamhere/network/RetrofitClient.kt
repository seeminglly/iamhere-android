//package com.example.iamhere.network
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    private const val BASE_URL = "http://34.64.121.178:8000/"  // ← 예시 GCP IP
//
//    val attendanceApi: AttendanceApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(AttendanceApi::class.java)
//    }
//}


package com.example.iamhere.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://34.64.121.178:8000/"  // ← 실제 서버 주소

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // ✅ 출석 API
    val attendanceApi: AttendanceApi by lazy {
        retrofit.create(AttendanceApi::class.java)
    }

    // ✅ 로그인 API
    val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }
}
