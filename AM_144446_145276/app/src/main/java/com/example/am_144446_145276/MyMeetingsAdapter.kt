package com.example.am_144446_145276

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.am_144446_145276.data.Meeting

class MyMeetingsAdapter(private val meetingList : ArrayList<Meeting>) : RecyclerView.Adapter<MyMeetingsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meeting_item,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = meetingList[position]
        holder.username.text = currentItem.hostName
        holder.details.text = currentItem.details
    }

    override fun getItemCount(): Int {
        return meetingList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val username : TextView = itemView.findViewById(R.id.item_username)
        val details : TextView = itemView.findViewById(R.id.item_city)
    }

}