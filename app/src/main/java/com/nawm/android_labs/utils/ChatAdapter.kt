package com.nawm.android_labs.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nawm.android_labs.R
import com.nawm.android_labs.databinding.ChatListItemBinding

class ChatAdapter(
    private val senders: Array<String>,
    private val messages: Array<String>,
    private val times: Array<String>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(chatListItemBinding: ChatListItemBinding) : RecyclerView.ViewHolder(chatListItemBinding.root) {
        val senderName: TextView = chatListItemBinding.senderName
        val lastMessage: TextView = chatListItemBinding.lastMessage
        val messageTime: TextView = chatListItemBinding.messageTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
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