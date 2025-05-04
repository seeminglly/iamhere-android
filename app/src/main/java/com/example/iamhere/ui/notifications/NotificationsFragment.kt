package com.example.iamhere.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamhere.R

class NotificationsFragment : Fragment() {

    private lateinit var alertAdapter: AlertAdapter
    private lateinit var alertRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ RecyclerView 연결
        alertRecyclerView = view.findViewById(R.id.alertRecyclerView)
        alertRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val alerts = mutableListOf(
            Alert("지문 인식 실패 - 홍길동", "09:01"),
            Alert("등록되지 않은 사용자 - 김철수", "09:03")
        )

        // ✅ 어댑터 설정 및 연결
        alertAdapter = AlertAdapter(alerts) { position ->
            alerts.removeAt(position)
            alertAdapter.notifyItemRemoved(position)
        }

        alertRecyclerView.adapter = alertAdapter

        val editButton = view.findViewById<Button>(R.id.btn_edit_attendance)
        editButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_edit_attendance)
        }

    }
}
