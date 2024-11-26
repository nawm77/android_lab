package com.nawm.android_labs.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nawm.android_labs.repository.SettingsRepository
import com.nawm.android_labs.databinding.FragmentSettingsBinding
import com.nawm.android_labs.fragments.CharactersFragment.Companion.FILE_NAME
import com.nawm.android_labs.utils.UserSettings
import kotlinx.coroutines.launch
import java.io.File

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var userSettings: UserSettings
    private lateinit var settingsRepository: SettingsRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSettings = UserSettings(requireContext())
        settingsRepository = SettingsRepository(requireContext())

        binding.saveButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            lifecycleScope.launch {
                userSettings.saveUsername(username)
                settingsRepository.saveTheme(binding.themeSwitch.isChecked)
            }
        }

        lifecycleScope.launch {
            binding.usernameEditText.setText(userSettings.getUsername())
            binding.themeSwitch.isChecked = settingsRepository.getTheme()
        }

        binding.deleteFileButton.setOnClickListener {
            deleteCharactersFile()
        }

        binding.restoreFileButton.setOnClickListener {
            restoreFile()
        }

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            FILE_NAME
        )

        val backupFile = File(requireContext().filesDir, FILE_NAME)
        if(backupFile.exists()) {
            binding.restoreFileButton.isEnabled = true
        }

        if (file.exists()) {
            binding.fileStatusTextView.text = "Файл $FILE_NAME найден"
            binding.deleteFileButton.isEnabled = true
        } else {
            binding.fileStatusTextView.text = "Файл '$FILE_NAME' не найден"
            binding.deleteFileButton.isEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteCharactersFile() {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            FILE_NAME
        )

        if (file.exists()) {
            val backupFile = File(requireContext().filesDir, FILE_NAME)

            try {
                file.inputStream().use { input ->
                    backupFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                Log.d("app", "Backup for file $FILE_NAME created successfully")

                val deleted = file.delete()
                if (deleted) {
                    Log.d("app", "File $FILE_NAME successfully deleted")
                    binding.fileStatusTextView.text = "Файл '$FILE_NAME' не найден"
                    binding.deleteFileButton.isEnabled = false
                    binding.restoreFileButton.isEnabled = true
                } else {
                    Log.d("app", "File $FILE_NAME cannot be deleted")
                }
            } catch (e: Exception) {
                Log.e("app", "Failed to create backup for file $FILE_NAME", e)
                binding.fileStatusTextView.text = "Ошибка создания резервной копии"
            }
        } else {
            Log.d("app", "File $FILE_NAME is not found")
        }
    }

    private fun restoreFile() {
        val backupFile = File(requireContext().filesDir, FILE_NAME)
        val originalFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            FILE_NAME
        )

        if (backupFile.exists()) {
            try {
                backupFile.inputStream().use { input ->
                    originalFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                Log.d("app", "File ${backupFile.path} $FILE_NAME successfully restored")
                backupFile.delete()
                binding.fileStatusTextView.text = "Файл $FILE_NAME найден"
                binding.deleteFileButton.isEnabled = true
                binding.restoreFileButton.isEnabled = false

            } catch (e: Exception) {
                Log.e("app", "Failed to restore file $FILE_NAME", e)
                binding.fileStatusTextView.text = "Ошибка восстановления файла"
            }
        } else {
            Log.d("app", "Backup file $FILE_NAME not found")
            binding.fileStatusTextView.text = "Резервный файл '$FILE_NAME' не найден"
        }
    }
}