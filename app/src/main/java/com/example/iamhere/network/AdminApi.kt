package com.example.iamhere.network

import com.example.iamhere.model.AdminSummary
import com.example.iamhere.model.Alert
import com.example.iamhere.model.AttendanceRecord
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface AdminApi {
    @GET("admin/attendances")
    fun getAttendances(): Call<List<AttendanceRecord>>

    @PUT("admin/attendance/{id}")
    fun updateAttendance(
        @Path("id") attendanceId: Long,
        @Body updated: Map<String, String> // e.g., {"status": "2차출석완료"}
    ): Call<Void>

    @GET("admin/summary")
    fun getSummary(): Call<AdminSummary>

    @GET("admin/alerts")
    fun getAlerts(): Call<List<Alert>>
}
