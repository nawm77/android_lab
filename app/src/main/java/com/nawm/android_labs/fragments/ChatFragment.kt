package com.nawm.android_labs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.nawm.android_labs.R
import java.util.HashMap

class ChatFragment : Fragment() {
    private lateinit var chatListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        chatListView = view.findViewById(R.id.chat_recycler_view)

        val senders: Array<String> = arrayOf("Tim Cook", "John Smith", "Jeremy Johnson")
        val messages: Array<String> = arrayOf("Hey! I just presented new iPhone", "Welcome to our chat!", "wtf is coming up?")
        val times: Array<String> = arrayOf("12:00", "12:10", "12:13")
        
        val chatList: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in senders.indices) {
            val chatMap = HashMap<String, String>()
            chatMap["sender"] = senders[i]
            chatMap["message"] = messages[i]
            chatMap["time"] = times[i]
            chatList.add(chatMap)
        }

        val adapter = SimpleAdapter(
            requireContext(),
            chatList,
            R.layout.chat_list_item,
            arrayOf("sender", "message", "time"),
            intArrayOf(R.id.sender_name, R.id.last_message, R.id.message_time)
        )
        chatListView.adapter = adapter

        return view
    }
}