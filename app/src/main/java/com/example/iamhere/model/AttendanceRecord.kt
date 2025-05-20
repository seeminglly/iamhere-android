package com.example.iamhere.model

data class AttendanceRecord(
    val attendance_id: Long,
    val name: String,
    val student_id: String,
    val status: String,
    val check_in: String
)