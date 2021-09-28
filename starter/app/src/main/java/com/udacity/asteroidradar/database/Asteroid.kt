package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "asteroids_table")
data class `Asteroid.kt`(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "code_name")
    val codename: String,

    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,

    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: Double,

    @ColumnInfo(name = "estimate_diameter")
    val estimatedDiameter: Double,

    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double,

    @ColumnInfo(name = "distance_from_earth")
    val distanceFromEarth: Double,

    @ColumnInfo(name = "is_potential_hazard")
    val isPotentiallyHazardous: Boolean
)