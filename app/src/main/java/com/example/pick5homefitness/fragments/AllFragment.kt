package com.example.pick5homefitness.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pick5homefitness.Exercise
import com.example.pick5homefitness.ExerciseAdapter
import com.example.pick5homefitness.R
import com.example.pick5homefitness.db.ExerciseDatabase
import kotlinx.android.synthetic.main.all_exercises.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/* Ryan Sandford, 2020-09-06
AllFragment contains a recycler view which holds
all the exercises in the database.*/
class AllFragment: Fragment(R.layout.all_exercises) {

    private lateinit var previewAdapter: ExerciseAdapter
    private var allExercises = mutableListOf<Exercise>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize an empty recycler view to prevent
        // crash while coroutine queries db
        initializeRecyclerView()
        loadRecyclerView()

        //on image click, navigate to FullScreenFragment to display image
        previewAdapter.setOnImageClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_allFragment
                    putIntArray("image", intArrayOf(it, returnId))
            }
            findNavController().navigate(
                R.id.action_allFragment_to_fullScreenFragment,
                bundle
            )
        }

        //on exercise click, navigate to ExerciseFragment to
        //display selected exercise
        previewAdapter.setOnPreviewClickListener {
            val bundle = Bundle().apply {
                putSerializable("exercise", it)
            }
            findNavController().navigate(
                R.id.action_allFragment_to_exerciseFragment,
                bundle
            )
        }
    }

    //initialize an empty recycler view
    private fun initializeRecyclerView() {
        previewAdapter = ExerciseAdapter(allExercises)
        rvAll.adapter = previewAdapter
        rvAll.layoutManager = LinearLayoutManager(activity)
    }

    //fill the recycler view with all exercises from the db
    private fun loadRecyclerView(){
        context?.let {
            MainScope().launch {
                val db = ExerciseDatabase.invoke(it)
                allExercises.removeAll(allExercises)
                allExercises.addAll(db.getExerciseDao().getAllExercises().toMutableList())
                previewAdapter.notifyDataSetChanged()
            }
        }
    }

}