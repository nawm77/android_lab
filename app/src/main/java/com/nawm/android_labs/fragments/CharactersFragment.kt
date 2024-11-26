package com.nawm.android_labs.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nawm.android_labs.databinding.FragmentCharactersBinding
import com.nawm.android_labs.domain.Character
import com.nawm.android_labs.utils.CharactersAdapter
import com.nawm.android_labs.utils.RetrofitNetwork
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream

class CharactersFragment : Fragment() {
    private val startIndex = 1151
    private val endIndex = 1200
    private var _binding: FragmentCharactersBinding? = null
    companion object {
        const val FILE_NAME = "characters_list_24.txt"
    }

    private val binding
        get() = _binding
            ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")

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
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                    FILE_NAME
                )

                if (file.exists()) {
                    Log.d("app", "File $FILE_NAME is already exists")
                    val charactersFromFile = readCharactersFromFile(file)
                    charactersAdapter = CharactersAdapter(charactersFromFile)
                    binding.charactersRecyclerView.adapter = charactersAdapter
                } else {
                    Log.d("app", "File $FILE_NAME isn't already exists")
                    val charactersFromApi = retrofitNetwork.getCharactersInRange(startIndex, endIndex)
                    charactersAdapter = CharactersAdapter(charactersFromApi)
                    binding.charactersRecyclerView.adapter = charactersAdapter

                    saveCharactersToFile(charactersFromApi)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun readCharactersFromFile(file: File): List<Character> {
        return try {
            val jsonString = file.readText()
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<List<Character>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveCharactersToFile(characters: List<Character>) {
        val fileName = FILE_NAME
        val directory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)
        if(file.exists()){
            Log.d("app", "File $FILE_NAME is already exists")
        }
        val json = Json { prettyPrint = true }
        val charactersJson = json.encodeToString(characters)
        try {
            FileOutputStream(file).use { outputStream ->
                outputStream.write(charactersJson.toByteArray())
            }
            Log.d("app", "File successfully saved: ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}