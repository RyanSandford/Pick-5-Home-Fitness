package com.example.pick5homefitness.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pick5homefitness.Exercise
import com.example.pick5homefitness.ExerciseAdapter
import com.example.pick5homefitness.ExerciseList
import com.example.pick5homefitness.R
import com.example.pick5homefitness.db.ExerciseDatabase
import kotlinx.android.synthetic.main.home.*
import kotlinx.coroutines.*
import kotlin.random.Random

/* Ryan Sandford, 2020-09-06
HomeFragment contains a recycler view which holds
holds 5 randomly generated exercises.*/
class HomeFragment : Fragment(R.layout.home) {

    private lateinit var previewAdapter: ExerciseAdapter
    private var picked = mutableListOf<Exercise>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        //on image click navigate to FullScreenFragment to display image
        previewAdapter.setOnImageClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_homeFragment
                putIntArray("image", intArrayOf(it, returnId))
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_fullScreenFragment,
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
                R.id.action_homeFragment_to_exerciseFragment,
                bundle
            )
        }

        //on click, reselect 5 random exercises for workout
        btnPick5.setOnClickListener{
            pick5()
        }
    }

    //initialize recyclerview to load previously selected exercises
    //or generate new ones if empty
   private fun setUpRecyclerView(){
       previewAdapter = ExerciseAdapter(picked)
       if(ExerciseList.selected.isEmpty()) {
           pick5()
       } else if (picked.isEmpty()){
           picked.addAll(ExerciseList.selected)
       }
       rvHome.adapter = previewAdapter
       rvHome.layoutManager = LinearLayoutManager(activity)
   }

    //randomly generate a workout of 5 exercises
   private fun pick5() {

        //remove any exercises already selected
       ExerciseList.selected.removeAll(picked)
       picked.removeAll(picked)

       context?.let {
           MainScope().launch {
               val db = ExerciseDatabase.invoke(it)
               val exerciseCount = db.getExerciseDao().countExercises()
               val randomIndices = mutableListOf<Int>()

               //randomly select 5 distinct primary keys
               for (i in 0..4){
                   var index = Random.nextInt(1, exerciseCount)
                   while (randomIndices.contains(index)){
                       index = Random.nextInt(1, exerciseCount)
                   }
                   randomIndices.add(index)
                   picked.add(db.getExerciseDao().getExerciseById(randomIndices[i]))
                   ExerciseList.selected.add(picked[i])
               }
               previewAdapter.notifyDataSetChanged()
           }
       }
   }

}