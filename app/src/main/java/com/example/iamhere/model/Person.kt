package com.example.iamhere.model

data class Person(val name: String, val id: Int, var status: String = "출석")

object PersonData {
    val sampleList = mutableListOf(
        Person("홍길동", 20220101, "출석"),
        Person("김영희", 20230101, "지각"),
        Person("박철수", 20210101, "결석"),
        Person("홍길동", 20220101, "출석"),
        Person("김영희", 20230101, "지각"),
        Person("박철수", 20210101, "결석")
    )
}
