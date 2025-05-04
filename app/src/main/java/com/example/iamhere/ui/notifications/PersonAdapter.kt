package com.example.iamhere.ui.notifications  // 혹은 실제 경로에 맞게 수정

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamhere.R
import com.example.iamhere.model.Person
class PersonAdapter(private var people: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.nameText)
        val statusText: TextView = view.findViewById(R.id.statusText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.nameText.text = person.name
        holder.statusText.text = person.status
    }

    override fun getItemCount() = people.size

    fun updateList(newList: List<Person>) {
        people = newList
        notifyDataSetChanged()
    }
}
