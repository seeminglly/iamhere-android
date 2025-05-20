package com.example.iamhere.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamhere.R
import com.example.iamhere.model.AttendanceRecord

class EditAttendanceAdapter(
    private val records: List<AttendanceRecord>,
    private val onStatusChange: (AttendanceRecord, String) -> Unit
) : RecyclerView.Adapter<EditAttendanceAdapter.ViewHolder>() {

    private val statuses = arrayOf("1차출석완료", "1차출석실패", "2차출석완료", "2차출석실패", "2차출석제외")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.txt_name)
        val idText: TextView = itemView.findViewById(R.id.txt_student_id)
        val statusSpinner: Spinner = itemView.findViewById(R.id.spinner_status)
        val timeText: TextView = itemView.findViewById(R.id.txt_check_in)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance_edit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = records.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.nameText.text = record.name
        holder.idText.text = record.student_id
        holder.timeText.text = "출석 시간: ${record.check_in}"

        val spinnerAdapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, statuses)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.statusSpinner.adapter = spinnerAdapter

        // 현재 상태 선택
        val currentIndex = statuses.indexOf(record.status)
        if (currentIndex != -1) holder.statusSpinner.setSelection(currentIndex)

        holder.statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, pos: Int, id: Long
            ) {
                val newStatus = statuses[pos]
                if (newStatus != record.status) {
                    onStatusChange(record, newStatus)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}
