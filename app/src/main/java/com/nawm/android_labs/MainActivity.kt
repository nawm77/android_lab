package com.nawm.android_labs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chatListView: ListView = findViewById(R.id.chat_recycler_view)
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
            this,
            chatList,
            R.layout.chat_list_item,
            arrayOf("sender", "message", "time"),
            intArrayOf(R.id.sender_name, R.id.last_message, R.id.message_time)
        )

        chatListView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in MainActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart called in MainActivity")
    }
}