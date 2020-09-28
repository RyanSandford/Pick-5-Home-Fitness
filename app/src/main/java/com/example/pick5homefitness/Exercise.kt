package com.example.pick5homefitness

import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises"
)

/* Ryan Sandford, 2020-09-06
Exercise is a data class corresponding to records in table
exercise_db.db with id as primary key.*/
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val description: String,
    val muscleGroups: String,
    val sets: String,
    val reps: String,
    val materials: String
) : Serializable
{
}