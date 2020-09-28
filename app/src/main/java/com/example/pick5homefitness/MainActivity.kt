package com.example.pick5homefitness

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pick5homefitness.db.ExerciseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


/* Ryan Sandford, 2020-09-06
 MainActivity sets up bottom nav menu;
 additionally MainActivity saves and retrieves favourited exercises
 from shared preferences.*/
class MainActivity : AppCompatActivity() {

    lateinit var sharedPref: SharedPreferences
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up bottom navigation menu
        bottomNavigationView.setupWithNavController(exerciseNavHostFragment.findNavController())

        //retrieve favourite exercises from shared preferences
        sharedPref = getSharedPreferences("favourites", Context.MODE_PRIVATE)
        getFavourites()
    }

    //invokes saveFavourites when app is paused
    override fun onPause() {
        super.onPause()
        val editor = sharedPref.edit()
        saveFavourites(editor)
    }

    //retrieve primary key favourited exercises from shared preferences
    //initialize favourites to contain the exercises corresponding to those primary keys
    private fun getFavourites(){

        //get primary keys
        val favIds: Set<String>? = sharedPref.getStringSet("favourites", null)
        favIds?.let{
            favIds.forEach {
                val id = it.toInt()

                //add the exercises corresponding to primary keys to favourites
                MainScope().launch {
                    val db = ExerciseDatabase.invoke(context)
                    ExerciseList.favourites.add(db.getExerciseDao().getExerciseById(id))
                }
            }
        }
    }

    //saves the primary key of favourited exercises to shared preferences
    private fun saveFavourites(editor: SharedPreferences.Editor) {

        //get primary keys of favourited exercises
        val idList = mutableListOf<String>()
        for (exercise in ExerciseList.favourites) {
            val favId = exercise.id
            idList.add(favId.toString())
        }

        //save primary keys to shared preferences
        editor.apply {
            val favMap: MutableSet<String> = idList.toMutableSet()
            favMap.addAll(idList)
            putStringSet("favourites", favMap)
            apply()
        }
    }

}