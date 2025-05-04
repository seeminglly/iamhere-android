package com.example.iamhere.ui.calendar

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class CircleDecorator(
    private val dates: Collection<CalendarDay>,
    private val color: Int
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.any { it == day }
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(15f, color)) // 아래 원 크기와 색
    }
}
