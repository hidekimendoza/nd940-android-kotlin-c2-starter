package com.udacity.asteroidradar.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid

class AsteroidAdapter: RecyclerView.Adapter<AsteroidViewHolder>() {

    var asteroids = listOf<Asteroid>()

    override fun getItemCount(): Int = asteroids.size

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        var currentAsteroid: Asteroid = asteroids.get(position)
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        TODO("Not yet implemented")
    }


}


class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}