package com.nawm.android_labs.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nawm.android_labs.databinding.ListItemBinding
import com.nawm.android_labs.domain.Character
import com.nawm.android_labs.entity.CharacterEntity

class CharactersAdapter(private val characters: MutableList<Character>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: Character) {
            binding.nameTextView.text = character.name
            binding.cultureTextView.text = "Culture: ${character.culture ?: "Unknown"}"
            binding.bornTextView.text = "Born: ${character.born ?: "Unknown"}"
            binding.titlesTextView.text = "Titles: ${character.titles.joinToString(", ")}"
            binding.aliasesTextView.text = "Aliases: ${character.aliases.joinToString(", ")}"
            binding.playedByTextView.text = "Played By: ${character.playedBy.joinToString(", ")}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCharacters: List<Character>) {
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        characters.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }
}