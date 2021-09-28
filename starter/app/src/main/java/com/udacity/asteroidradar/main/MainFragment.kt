package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

private const val TAG = "MainFragment"

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var asteroidsAdapter: AsteroidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        asteroidsAdapter = AsteroidAdapter()
        binding.asteroidRecycler.adapter = asteroidsAdapter

        viewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            Log.i(TAG, "Number of asteroids updated: {${asteroidList}}")
            asteroidList?.let {
                asteroidsAdapter.submitList(it)
            }
        })

        viewModel.error_message.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, "Error trying to get data $errorMessage", Toast.LENGTH_SHORT)
                .show()
            Log.e(TAG, "Error getting asteroids: {${errorMessage}}")
        })



        viewModel.getAsteroids()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
