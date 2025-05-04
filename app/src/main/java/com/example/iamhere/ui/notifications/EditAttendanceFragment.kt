package com.example.iamhere.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamhere.R
import com.example.iamhere.model.PersonData

class EditAttendanceFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.myRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = PersonAdapter(PersonData.sampleList)  // ✅ PersonAdapter 연결
        recyclerView.adapter = adapter

        // ✅ 관리자홈 버튼 클릭 처리
        val adminHomeBtn = view.findViewById<View>(R.id.btn_admin_home)
        adminHomeBtn.setOnClickListener {
           findNavController().navigate(R.id.navigation_admin)
        }
    }
}

