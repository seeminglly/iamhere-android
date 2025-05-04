package com.example.iamhere.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.iamhere.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

    private lateinit var todayCard: LinearLayout
    private lateinit var statsCard: LinearLayout
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        todayCard = view.findViewById(R.id.todayCard)
        statsCard = view.findViewById(R.id.statsCard)
        pieChart = view.findViewById(R.id.pieChart)

        view.findViewById<MaterialButton>(R.id.todayButton).setOnClickListener {
            flipCard(true)
        }
        view.findViewById<MaterialButton>(R.id.statsButton).setOnClickListener {
            flipCard(false)
            statsCard.postDelayed({ drawPieChart() }, 600)
        }

        view.findViewById<MaterialButton>(R.id.todayButton2).setOnClickListener {
            flipCard(true)
        }
        view.findViewById<MaterialButton>(R.id.statsButton2).setOnClickListener {
            flipCard(false)
            statsCard.postDelayed({ drawPieChart() }, 600)
        }

        return view
    }

    private fun flipCard(showFront: Boolean) {
        val scale = resources.displayMetrics.density
        todayCard.cameraDistance = 8000 * scale
        statsCard.cameraDistance = 8000 * scale

        if (showFront) {
            todayCard.visibility = View.VISIBLE
            statsCard.animate().rotationY(90f).setDuration(300).withEndAction {
                statsCard.visibility = View.GONE
                statsCard.rotationY = 0f
                todayCard.rotationY = -90f
                todayCard.animate().rotationY(0f).setDuration(300).start()
            }.start()
        } else {
            statsCard.visibility = View.VISIBLE
            todayCard.animate().rotationY(-90f).setDuration(300).withEndAction {
                todayCard.visibility = View.GONE
                todayCard.rotationY = 0f
                statsCard.rotationY = 90f
                statsCard.animate().rotationY(0f).setDuration(300).start()
            }.start()
        }
    }

    private fun drawPieChart() {
        val entries = listOf(
            PieEntry(78.3f, "출석"),
            PieEntry(8.7f, "지각"),
            PieEntry(13.0f, "결석")
        )

        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(Color.GREEN, Color.YELLOW, Color.RED)
            sliceSpace = 3f
        }

        pieChart.apply {
            data = PieData(dataSet).apply {
                setValueTextSize(14f)
                setValueTextColor(Color.BLACK)
            }
            description.isEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.isWordWrapEnabled = true
            legend.textSize = 14f
            invalidate()
        }
    }
}
