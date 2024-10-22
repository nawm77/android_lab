package com.nawm.android_labs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nawm.android_labs.R

class OnboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboard, container, false)

        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.SignInFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in OnboardFragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in OnboardFragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in OnboardFragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in OnboardFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in OnboardFragment")
    }
}