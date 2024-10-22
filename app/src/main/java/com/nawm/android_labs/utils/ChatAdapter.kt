package com.nawm.android_labs.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nawm.android_labs.R

class ChatAdapter(
    private val senders: Array<String>,
    private val messages: Array<String>,
    private val times: Array<String>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        val messageTime: TextView = itemView.findViewById(R.id.message_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_list_item, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.senderName.text = senders[position]
        holder.lastMessage.text = messages[position]
        holder.messageTime.text = times[position]
    }

    override fun getItemCount(): Int {
        return senders.size
    }
}