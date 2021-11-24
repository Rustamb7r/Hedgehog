package com.example.Hedgehog_Test.framework.ui.joke

import androidx.recyclerview.widget.RecyclerView
import com.example.Hedgehog_Test.business.domain.Joke
import com.example.Hedgehog_Test.databinding.ItemJokeBinding

class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke:Joke){
        binding.textView.text = joke.joke
    }
}