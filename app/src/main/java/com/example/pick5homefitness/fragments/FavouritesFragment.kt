package com.example.pick5homefitness.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pick5homefitness.ExerciseAdapter
import com.example.pick5homefitness.ExerciseList
import com.example.pick5homefitness.R
import kotlinx.android.synthetic.main.favourites.*


/* Ryan Sandford, 2020-09-06
FavouritesFragment contains a recycler view which holds
all favourited the exercises.*/
class FavouritesFragment: Fragment(R.layout.favourites) {
    private lateinit var previewAdapter: ExerciseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        displayEmpty()

        //on image click, navigate to FullScreenFragment to display image
        previewAdapter.setOnImageClickListener {
            val bundle = Bundle().apply {
                val returnId = R.id.action_fullScreenFragment_to_favouritesFragment
                putIntArray("image", intArrayOf(it, returnId))
            }
            findNavController().navigate(
                R.id.action_favouritesFragment_to_fullScreenFragment,
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
                R.id.action_favouritesFragment_to_exerciseFragment,
                bundle
            )
        }
    }

    //initialize recycler view
    private fun setUpRecyclerView(){
        previewAdapter =
            ExerciseAdapter(ExerciseList.favourites)
        rvFavourites.adapter = previewAdapter
        rvFavourites.layoutManager = LinearLayoutManager(activity)
    }

    //display text view when favourites are empty
    private fun displayEmpty(){
        if (ExerciseList.favourites.isEmpty()) {
            rvFavourites.visibility = View.GONE;
            tvEmptyFav.visibility = View.VISIBLE;
        }
        else {
            rvFavourites.visibility = View.VISIBLE;
            tvEmptyFav.visibility = View.GONE;
        }
    }

}