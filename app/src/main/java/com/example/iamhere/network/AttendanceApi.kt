package com.example.iamhere.network
import com.example.iamhere.model.Attendance
import com.example.iamhere.model.CalendarRecord
import com.example.iamhere.model.Statistics  // ✅ 통계 데이터 모델도 import
import com.example.iamhere.model.TodayLecture

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AttendanceApi {

    @GET("attendance/today")
    fun getTodayAttendance(
        @Query("user_id") userId: Int
    ): Call<List<Attendance>>

    // ✅ 출석 통계 API 추가
    @GET("attendance/statistics")
    fun getStatistics(
        @Query("user_id") userId: Int
    ): Call<Statistics>

    @GET("attendance/today-lecture")
    fun getTodayLecture(@Query("user_id") userId: Int): Call<TodayLecture>

    @GET("attendance/calendar")
    fun getCalendarData(
        @Query("user_id") userId: Int
    ): Call<List<CalendarRecord>>

}
