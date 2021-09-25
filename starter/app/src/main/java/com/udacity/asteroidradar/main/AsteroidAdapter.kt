package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    var asteroids = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = asteroids.size

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        var currentAsteroid: Asteroid = asteroids[position]
        holder.bind(currentAsteroid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }


    class AsteroidViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val code_name_textview: TextView = itemView.findViewById(R.id.code_name_textview)
        val approach_date_textview: TextView = itemView.findViewById(R.id.approach_date_textview)
        val hazardous_level_imageview: ImageView =
            itemView.findViewById(R.id.hazardous_level_imageview)

        fun bind(
            currentAsteroid: Asteroid
        ) {
            code_name_textview.text = currentAsteroid.codename
            approach_date_textview.text = currentAsteroid.closeApproachDate
            if (currentAsteroid.isPotentiallyHazardous) {
                hazardous_level_imageview.setImageResource(R.drawable.asteroid_hazardous)
            } else {
                hazardous_level_imageview.setImageResource(R.drawable.asteroid_safe)
            }
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val asteroidView =
                    layoutInflater.inflate(R.layout.asteroid_list_item, parent, false)
                return AsteroidViewHolder(asteroidView)
            }
        }
    }
}