package com.example.iamhere.model
data class AdminSummary(
    val total: Int,
    val attended: Int,
    val late: Int,
    val absent: Int,
    val lecture_date: String,
    val lecture_info: String
)
