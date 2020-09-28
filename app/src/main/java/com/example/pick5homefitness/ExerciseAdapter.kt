package com.example.pick5homefitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_preview.view.*

/* Ryan Sandford, 2020-09-06
Exercise Adapter is the adapter used to hold exercises, used
in the recycler view corresponding to each of the four fragments
in the bottom nav menu.*/
class ExerciseAdapter
    (var exercises: MutableList<Exercise>): RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    // on click listeners allow click behaviour to vary between fragments
    private var onPreviewClickListener: ((Exercise) -> Unit)? = null
    private var onImageClickListener: ((Int) -> Unit)? = null

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_preview, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.itemView.apply {

            //initialize elements in exercise_preview.xml
            tvPreviewTitle.text = exercises[position].title
            tvPreviewDescription.text = exercises[position].description
            tvPreviewSets.text = exercises[position].sets
            tvPreviewReps.text = exercises[position].reps
            val exerciseImages = ExerciseList.images[exercises[position].id]
            exerciseImages?.let{
                ivPreviewImage.setImageResource(exerciseImages[0])
            }

            //initialize favourites toggle
            btnFavourite.isActivated = exercises[position] in ExerciseList.favourites
            setUpFavouritesToogle(this, position)

            //listen for click on an exercise
            setOnClickListener {
                onPreviewClickListener?.let {
                    it(exercises[position])
                }
            }

            //listen for click on an image
            ivPreviewImage.setOnClickListener {
                onImageClickListener?.let {
                    exerciseImages?.get(0)?.let { it1 -> it(it1) }
                }
            }
        }
        holder.setIsRecyclable(false)

    }

    //dynamically set a on click listener on the exercise preview whose behaviour can vary
    fun setOnPreviewClickListener(listener: (Exercise) -> Unit) {
        onPreviewClickListener = listener
    }

    //dynamically set a on click listener on the image preview whose behaviour can vary
    fun setOnImageClickListener(listener: (Int) -> Unit) {
        onImageClickListener = listener
    }

    //toggle favourite icon to change on click
    //add/ remove corresponding exercise from favourites list
    private fun setUpFavouritesToogle(view: View, position: Int) {
        view.btnFavourite.setOnClickListener {
            view.btnFavourite.isActivated = !view.btnFavourite.isActivated
                if (exercises[position] in ExerciseList.favourites) {
                    ExerciseList.favourites.remove(exercises[position])
                } else {
                    ExerciseList.favourites.add(exercises[position])
                }
            this.notifyDataSetChanged()
        }

    }
}


