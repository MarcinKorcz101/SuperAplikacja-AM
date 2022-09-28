package com.example.am_144446_145276

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.am_144446_145276.data.Meeting

class MyMeetingsAdapter(private val meetingList : ArrayList<Meeting>) : RecyclerView.Adapter<MyMeetingsAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meeting_item,
        parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = meetingList[position]
        holder.username.text = currentItem.hostName
//        holder.details.text = currentItem.details
        holder.meetingDateView.text = currentItem.dateTime
        var opponentName = currentItem.opponent
        if(opponentName == "null"){
            opponentName = "-"
        }
        holder.opponentName.text = opponentName
//        var result = currentItem.result
//        if(result == "null"){
//            result = "in progress"
//        }
//        holder.result.text = result
    }

    override fun getItemCount(): Int {
        return meetingList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView)
    {
        val username : TextView = itemView.findViewById(R.id.item_username)
//        val details : TextView = itemView.findViewById(R.id.vs_text)
        val meetingDateView : TextView = itemView.findViewById(R.id.item_date)
        val opponentName: TextView = itemView.findViewById(R.id.opponent_username)
        val result: TextView = itemView.findViewById(R.id.result_text)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}