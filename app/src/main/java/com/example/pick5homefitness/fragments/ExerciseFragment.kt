package com.example.pick5homefitness.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pick5homefitness.ExerciseList
import com.example.pick5homefitness.R
import kotlinx.android.synthetic.main.exercise.*

/* Ryan Sandford, 2020-09-06
ExerciseFragment displays all information pertaining to a given exercise.
Takes an exercise as a nav argument.*/
class ExerciseFragment: Fragment(R.layout.exercise) {

    //exercise to display
    private val args: ExerciseFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize elements in exercise.xml
        val exercise = args.exercise
        tvTitle.text = exercise.title
        tvDescription.text = exercise.description
        tvMuscleGroups.text = "Muscle Groups: ${exercise.muscleGroups}"
        tvSets.text = exercise.sets
        tvReps.text = exercise.reps
        if (exercise.materials.length > 1){
            tvMaterials.text = "Materials: ${exercise.materials}"}
        else{
            tvMaterials.text = ""
        }
        val exerciseImages = ExerciseList.images[exercise.id]
        exerciseImages?.let{
            ivDemo1.setImageResource(exerciseImages[1])
            ivDemo2.setImageResource(exerciseImages[0])
        }

        //on image click navigate to FullScreenFragment to display image
        ivDemo1.setOnClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_exerciseFragment
                exerciseImages?.get(1)?.let { it1 ->
                    putIntArray("image", intArrayOf(it1, returnId))
                }
            }
            findNavController().navigate(
                R.id.action_exerciseFragment_to_fullScreenFragment,
                bundle
            )
        }

        //on image click navigate to FullScreenFragment to display image
        ivDemo2.setOnClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_exerciseFragment
                exerciseImages?.get(0)?.let { it0 ->
                    putIntArray("image", intArrayOf(it0, returnId))
                }
            }
            findNavController().navigate(
                R.id.action_exerciseFragment_to_fullScreenFragment,
                bundle
            )
        }
    }

}