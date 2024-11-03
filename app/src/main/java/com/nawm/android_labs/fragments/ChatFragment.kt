package com.nawm.android_labs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nawm.android_labs.databinding.FragmentChatBinding
import com.nawm.android_labs.utils.ChatAdapter

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")
    private lateinit var chatRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        chatRecyclerView = binding.chatRecyclerView
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val senders = arrayOf("Tim Cook", "John Smith", "Jeremy Johnson")
        val messages = arrayOf("Hey! I just presented new iPhone", "Welcome to our chat!", "wtf is coming up?")
        val times = arrayOf("12:00", "12:10", "12:13")

        val adapter = ChatAdapter(senders, messages, times)
        chatRecyclerView.adapter = adapter

        return binding.root
    }
}