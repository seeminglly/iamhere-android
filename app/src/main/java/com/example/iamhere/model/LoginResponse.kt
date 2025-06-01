package com.example.iamhere.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("user_name")
    val userName: String,

    @SerializedName("user_id") // 내부 DB의 PK
    val userId: Int,  // ✅ 변경됨 (FastAPI에서는 int로 반환)

    @SerializedName("student_number")
    val studentNumber: String?,  // ✅ 학번

    @SerializedName("role")
    val role: String?,           // ✅ 사용자 역할 (학생 / 교수)

//    @SerializedName("today_attendance")
//    val today_attendance: TodayLecture?  // ✅ 오늘 강의 정보
)
