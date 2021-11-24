package com.example.Hedgehog_Test.framework.ui.joke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.Hedgehog_Test.business.domain.Joke
import com.example.Hedgehog_Test.databinding.ItemJokeBinding

class JokesAdapter : RecyclerView.Adapter<JokeViewHolder>() {
    var jokesList = ArrayList<Joke>()
        private set
    fun setJokes(jokesList: List<Joke>){
        this.jokesList.clear()
        this.jokesList.addAll(jokesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeBinding.inflate(layoutInflater, parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokesList[position])
    }

    override fun getItemCount(): Int {
        return  jokesList.size
    }
}