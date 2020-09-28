package com.example.pick5homefitness.fragments


import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pick5homefitness.Exercise
import com.example.pick5homefitness.ExerciseAdapter
import com.example.pick5homefitness.R
import com.example.pick5homefitness.db.ExerciseDatabase
import kotlinx.android.synthetic.main.search.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/* Ryan Sandford, 2020-09-06
SearchFragment allows users to search for exercises in the database.*/
class SearchFragment: Fragment(R.layout.search) {

    private var results = mutableListOf<Exercise>()
    private lateinit var previewAdapter: ExerciseAdapter
    private lateinit var query: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        listenForQuery()

        //on image click navigate to FullScreenFragment to display image
        previewAdapter.setOnImageClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_searchFragment
                putIntArray("image", intArrayOf(it, returnId))
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_fullScreenFragment,
                bundle
            )
        }

        //on exercise click navigate to ExerciseFragment to
        //display selected exercise
        previewAdapter.setOnPreviewClickListener {
            val bundle = Bundle().apply {
                putSerializable("exercise", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_exerciseFragment,
                bundle
            )
        }
    }

    //initialize recyclerview
    private fun setUpRecyclerView(){
        previewAdapter = ExerciseAdapter(results)
        rvSearch.adapter = previewAdapter
        rvSearch.layoutManager = LinearLayoutManager(activity)
    }

    //wait for query in edit text and search action
    private fun listenForQuery(){
        etSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            return@OnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    try {
                        query = etSearch.text.toString()
                        search(query)
                    } catch (e: IllegalStateException) {
                        Toast.makeText(activity, "Invalid Search", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                else -> false
            }
        })
    }

    //search database for query
    //don't allow user to access database directly
    private fun search(query: String){
        //remove previous search results
        results.removeAll(results)

        context?.let {
            MainScope().launch {
                val db = ExerciseDatabase.invoke(it)

                //db is not big enough to warrant only selecting columns matched on
                val exercises = db.getExerciseDao().getAllExercises()

                for (exercise in exercises){
                    //search criteria
                    if (exercise.title.contains(query, ignoreCase = true)
                        || exercise.muscleGroups.contains(query, ignoreCase = true)
                        || exercise.materials.contains(query, ignoreCase = true)
                        || query.contains(exercise.title, ignoreCase = true)
                        || query.contains(exercise.muscleGroups, ignoreCase = true)
                    ){
                        results.add(exercise)
                    }
                }
                previewAdapter.notifyDataSetChanged()
            }
        }

    }

}

