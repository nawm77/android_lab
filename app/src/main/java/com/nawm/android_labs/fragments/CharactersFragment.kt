package com.nawm.android_labs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nawm.android_labs.databinding.FragmentCharactersBinding
import com.nawm.android_labs.utils.CharactersAdapter
import com.nawm.android_labs.utils.RetrofitNetwork
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")

    private val retrofitNetwork by lazy { RetrofitNetwork() }
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val characters = retrofitNetwork.getCharactersInRange(1151, 1200)
                charactersAdapter = CharactersAdapter(characters)
                binding.charactersRecyclerView.adapter = charactersAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}