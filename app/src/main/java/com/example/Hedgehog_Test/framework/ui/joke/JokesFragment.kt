package com.example.Hedgehog_Test.framework.ui.joke

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Hedgehog_Test.databinding.FragmentJokesBinding
import java.lang.Exception

class JokesFragment : Fragment() {

    private lateinit var jokesViewModel: JokesViewModel
    private var binding: FragmentJokesBinding? = null
    private var adapter: JokesAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        jokesViewModel =
            ViewModelProvider(this).get(JokesViewModel::class.java)

        binding = FragmentJokesBinding.inflate(inflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = JokesAdapter()
        binding?.recyclerView?.adapter = adapter

        subscribeObservers()

        if (savedInstanceState == null) { // checking fragment creating first time
            jokesViewModel.fetchJokes()
        }

        binding?.button?.setOnClickListener {
            try {
                val jokesNumber = binding?.editText?.text.toString().toInt()
                jokesViewModel.loadJokesByNumber(jokesNumber)
            } catch (e: Exception) {
                Log.w("", "cant cast edittext text to Int")
            }
        }
    }

    private fun subscribeObservers(){
        jokesViewModel.jokeList.observe(viewLifecycleOwner){
            adapter?.setJokes(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}