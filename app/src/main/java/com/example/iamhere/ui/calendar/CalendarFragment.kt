package com.example.iamhere.ui.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.iamhere.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        // 캘린더 연결
        val calendarView = view.findViewById<MaterialCalendarView>(R.id.calendarView)

        // ✅ 출석 통계 텍스트뷰 연결
        val attendanceCount = view.findViewById<TextView>(R.id.attendance_count)
        val lateCount = view.findViewById<TextView>(R.id.late_count)
        val absentCount = view.findViewById<TextView>(R.id.absent_count)
        val worstDay = view.findViewById<TextView>(R.id.worst_day)

        // ✅ 예시 데이터 설정
        attendanceCount.text = "출석 횟수: 10회"
        lateCount.text = "지각 횟수: 1회"
        absentCount.text = "결석 횟수: 0회"
        worstDay.text = "가장 출석률이 좋지 않은 요일: 월요일"

        // 날짜 클릭 시 Toast 표시
        calendarView.setOnDateChangedListener { _, date, _ ->
            Toast.makeText(
                requireContext(),
                "${date.year}-${date.month + 1}-${date.day}",
                Toast.LENGTH_SHORT
            ).show()
        }

        val attendanceDates = listOf(
            CalendarDay.from(2025, 5, 25),
            CalendarDay.from(2025, 5, 26)
        )
        val lateDates = listOf(
            CalendarDay.from(2025, 5, 27)
        )
        val absenceDates = listOf(
            CalendarDay.from(2025,5,30)
        )

        calendarView.addDecorator(CircleDecorator(attendanceDates, Color.GREEN))
        calendarView.addDecorator(CircleDecorator(lateDates, Color.YELLOW))
        calendarView.addDecorator(CircleDecorator(absenceDates, Color.RED))


        return view
    }

}
