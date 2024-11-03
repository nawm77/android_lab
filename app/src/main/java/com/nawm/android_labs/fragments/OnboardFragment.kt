package com.nawm.android_labs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nawm.android_labs.R
import com.nawm.android_labs.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {
    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)

        binding.nextButton.setOnClickListener {
            val action = OnboardFragmentDirections.navigateFromOnBoardToSignIn(null)
            findNavController().navigate(action)
        }

        binding.viewCharacterButton.setOnClickListener {
            val action = OnboardFragmentDirections.navigateFromOnBoardToCharacters()
            findNavController().navigate(action)
        }

        return binding.root
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