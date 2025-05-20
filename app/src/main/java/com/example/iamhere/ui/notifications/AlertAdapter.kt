package com.example.iamhere.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamhere.R
import com.example.iamhere.model.Alert


class AlertAdapter(
    alerts: MutableList<Alert>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<AlertAdapter.AlertViewHolder>() {

    private val alerts = alerts  // 👉 내부 필드로 저장

    inner class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.alertMessage)
        val timeText: TextView = itemView.findViewById(R.id.alertTime)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        val alert = alerts[position]
        holder.messageText.text = alert.message
        holder.timeText.text = alert.time
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = alerts.size

    fun removeItem(position: Int) {
        alerts.removeAt(position)
        notifyItemRemoved(position)
    }

}
