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
import com.nawm.android_labs.App
import com.nawm.android_labs.dao.CharacterDao
import com.nawm.android_labs.databinding.FragmentCharactersBinding
import com.nawm.android_labs.domain.Character
import com.nawm.android_labs.domain.Character.Companion.toEntity
import com.nawm.android_labs.entity.CharacterEntity.Companion.toDomainCharacter
import com.nawm.android_labs.utils.CharactersAdapter
import com.nawm.android_labs.utils.RetrofitNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val characterDao: CharacterDao by lazy {
        (requireActivity().application as App).getDb().characterDao()
    }

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
        setupRecyclerView()
        lifecycleScope.launch(Dispatchers.IO) {
            loadCharacters(startIndex, endIndex)
        }

        lifecycleScope.launchWhenStarted {
            characterDao.getAllCharactersFlow().collect { charactersFromDb ->
                val characterResponses = charactersFromDb.map {
                    Character(
                        name = it.name,
                        culture = it.culture,
                        born = it.born,
                        titles = it.titles.split(",").filter { title -> title.isNotEmpty() },
                        aliases = it.aliases.split(",").filter { alias -> alias.isNotEmpty() },
                        playedBy = it.playedBy.split(",").filter { actor -> actor.isNotEmpty() }
                    )
                }
                charactersAdapter.setData(characterResponses)
            }
        }

        binding.retrofit.setOnClickListener {
            val page = binding.pageInput.text.toString().toIntOrNull()
            if (page != null && page > 0) {
                val start: Int = (page-1)*50+1
                val end: Int = page*50
                Log.d("app", "Load extra data for interval $start : $end")
                lifecycleScope.launch(Dispatchers.IO) {
                    loadCharacters(start, end)
                }
            } else {
                binding.pageInput.error = "Введите корректный номер страницы (положительное число)"
            }
        }

        binding.clearDB.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                clearDB()
            }
        }
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersAdapter(mutableListOf())
        binding.charactersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter
        }
    }

    private suspend fun clearDB() {
        withContext(Dispatchers.IO) {
            characterDao.deleteAllCharacters()
        }
        charactersAdapter.clearData()
    }

    private suspend fun loadCharacters(start: Int, end: Int) {
        try {
            val charactersFromDb = withContext(Dispatchers.IO) {
                characterDao.getCharactersInRange(start, end)
            }

            if (charactersFromDb.isNotEmpty()) {
                Log.d("app", "Characters loaded from database")
                withContext(Dispatchers.Main) {
                    charactersAdapter.updateData(charactersFromDb.map { it.toDomainCharacter() })
                }
            } else {
                Log.d("app", "No characters in database, fetching from API")
                val charactersFromApi = withContext(Dispatchers.IO) {
                    retrofitNetwork.getCharactersInRange(start, end)
                }
                withContext(Dispatchers.IO) {
                    val entities = charactersFromApi.mapIndexed {id, character -> character.toEntity(id+start) }
                    characterDao.insertCharacters(entities)
                }
                withContext(Dispatchers.Main) {
                    charactersAdapter.updateData(charactersFromApi)
                }
            }
        } catch (e: Exception) {
            Log.e("app", "Error loading characters: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun saveCharactersToFile(characters: List<Character>) {
        val directory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, FILE_NAME)
        val json = Json { prettyPrint = true }
        val charactersJson = json.encodeToString(characters)

        try {
            FileOutputStream(file).use { outputStream ->
                outputStream.write(charactersJson.toByteArray())
            }
            Log.d("app", "File successfully saved: ${file.absolutePath}")
        } catch (e: Exception) {
            Log.e("app", "Error saving file: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}