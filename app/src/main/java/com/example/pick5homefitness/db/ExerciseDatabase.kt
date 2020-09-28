package com.example.pick5homefitness.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pick5homefitness.Exercise

@Database(
    entities = [Exercise::class],
    version = 9
)

/* Ryan Sandford, 2020-09-06
Database for exercises.*/
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun getExerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var instance: ExerciseDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also{instance = it}
        }

        private fun createDatabase(context: Context): ExerciseDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ExerciseDatabase::class.java,
                "exercise_db.db"
            ).createFromAsset("exercise_db.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}