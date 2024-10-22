package com.nawm.android_labs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nawm.android_labs.R
import com.nawm.android_labs.utils.ChatAdapter

class ChatFragment : Fragment() {
    private lateinit var chatRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        chatRecyclerView = view.findViewById(R.id.chat_recycler_view)
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val senders = arrayOf("Tim Cook", "John Smith", "Jeremy Johnson")
        val messages = arrayOf("Hey! I just presented new iPhone", "Welcome to our chat!", "wtf is coming up?")
        val times = arrayOf("12:00", "12:10", "12:13")

        val adapter = ChatAdapter(senders, messages, times)
        chatRecyclerView.adapter = adapter

        return view
    }
}