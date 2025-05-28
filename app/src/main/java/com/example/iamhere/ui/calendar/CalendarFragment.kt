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
import com.example.iamhere.model.CalendarRecord
import com.example.iamhere.network.AttendanceApi
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val calendarView = view.findViewById<MaterialCalendarView>(R.id.calendarView)
        val attendanceCount = view.findViewById<TextView>(R.id.attendance_count)
        val lateCount = view.findViewById<TextView>(R.id.late_count)
        val absentCount = view.findViewById<TextView>(R.id.absent_count)
        val worstDay = view.findViewById<TextView>(R.id.worst_day)

        // 예시 통계 텍스트
        attendanceCount.text = "출석 횟수: 0회"
        lateCount.text = "지각 횟수: 0회"
        absentCount.text = "결석 횟수: 0회"
        worstDay.text = "가장 출석률이 좋지 않은 요일: -"

        calendarView.setOnDateChangedListener { _, date, _ ->
            Toast.makeText(
                requireContext(),
                "${date.year}-${date.month + 1}-${date.day}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // ✅ Retrofit으로 API 호출 및 데코레이터 적용
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.64.121.178:8000/")  // 여기를 본인의 FastAPI 서버 주소로 수정
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(AttendanceApi::class.java)
        val userId = 1  // 테스트용 사용자 ID

        api.getCalendarData(userId).enqueue(object : Callback<List<CalendarRecord>> {
            override fun onResponse(
                call: Call<List<CalendarRecord>>,
                response: Response<List<CalendarRecord>>
            ) {
                if (response.isSuccessful) {
                    val records = response.body() ?: return

                    val attendanceDates = mutableListOf<CalendarDay>()
                    val lateDates = mutableListOf<CalendarDay>()
                    val absenceDates = mutableListOf<CalendarDay>()

                    for (record in records) {
                        val parts = record.date.split("-").map { it.toInt() }
                        val day = CalendarDay.from(parts[0], parts[1] - 1, parts[2])  // month -1

                        when (record.status) {
                            "attendance" -> attendanceDates.add(day)
                            "late" -> lateDates.add(day)
                            "absent" -> absenceDates.add(day)
                        }
                    }

                    calendarView.addDecorator(CircleDecorator(attendanceDates, Color.GREEN))
                    calendarView.addDecorator(CircleDecorator(lateDates, Color.YELLOW))
                    calendarView.addDecorator(CircleDecorator(absenceDates, Color.RED))

                    // 통계 수치 갱신
                    attendanceCount.text = "출석 횟수: ${attendanceDates.size}회"
                    lateCount.text = "지각 횟수: ${lateDates.size}회"
                    absentCount.text = "결석 횟수: ${absenceDates.size}회"
                } else {
                    Toast.makeText(requireContext(), "서버 오류: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CalendarRecord>>, t: Throwable) {
                Toast.makeText(requireContext(), "연결 실패: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        return view
    }
}
