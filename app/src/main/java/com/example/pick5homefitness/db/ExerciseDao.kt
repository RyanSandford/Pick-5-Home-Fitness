package com.example.pick5homefitness.db

import androidx.room.*
import com.example.pick5homefitness.Exercise

@Dao
/* Ryan Sandford, 2020-09-06
ExerciseDao contains basic queries*/
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercises(): Array<Exercise>

    @Query("SELECT * FROM exercises WHERE id = :ID")
    suspend fun getExerciseById(ID: Int): Exercise

    @Query("SELECT COUNT (*) FROM exercises")
    suspend fun countExercises(): Int

}