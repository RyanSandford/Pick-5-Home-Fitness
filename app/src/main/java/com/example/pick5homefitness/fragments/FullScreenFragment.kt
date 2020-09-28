package com.example.pick5homefitness.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pick5homefitness.Exercise
import com.example.pick5homefitness.ExerciseList
import com.example.pick5homefitness.R
import com.example.pick5homefitness.db.ExerciseDatabase
import kotlinx.android.synthetic.main.full_screen.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/* Ryan Sandford, 2020-09-06
FullScreenFragment displays a larger version of the image
passed as a nav argument. Upon image click returns to the previous
fragment.*/
class FullScreenFragment: Fragment(R.layout.full_screen)  {

    //array containing id of image and return fragment path
    private val args: FullScreenFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageId = args.image[0]
        ivFull.setImageResource(imageId)

        //return to previous fragment upon image click
        ivFull.setOnClickListener {
            val dest = args.image[1]
            returnToFragment(imageId, dest)
        }
    }

    //get exercise corresponding to image
    private fun getKey(hashMap: Map<Int, Array<Int>>, target: Int): Int {
        return hashMap.filter {
            target == it.value[0] || target == it.value[1]}.keys.first()
    }

    //return to previous fragment. When fragment is ExerciseFragment
    //pass exercise corresponding to image as nav argument.
    private fun returnToFragment(imageId: Int, dest: Int){
        if (dest == R.id.action_fullScreenFragment_to_exerciseFragment){
            context?.let {
                MainScope().launch {
                    val key = getKey(ExerciseList.images, imageId)
                    val db = ExerciseDatabase.invoke(it)
                    val exercise: Exercise = db.getExerciseDao().getExerciseById(key)
                    val bundle = Bundle().apply {
                        putSerializable("exercise", exercise)
                    }
                    findNavController().navigate(dest, bundle)
                }
            }
        }else{
            findNavController().navigate(dest)
        }
    }
}