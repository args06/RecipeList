package com.example.recipelist.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipelist.R
import com.example.recipelist.adapter.RecipeAdapter.ViewHolder
import com.example.recipelist.model.RecipeResponseItem


class RecipeAdapter(private val recipe: List<RecipeResponseItem>) : RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvRecipeTitle: TextView
        private var tvRecipeHeadline: TextView
        private var tvCalories: TextView
        private var tvCarbos: TextView
        private var tvFats: TextView
        private var tvProtein: TextView
        private var tvTime: TextView
        private var tvDifficulty: TextView
        private var tvDesc: TextView
        private var btnShowmore: TextView

        @SuppressLint("SetTextI18n")
        fun bind(item: RecipeResponseItem) {
            Glide.with(itemView.context)
                    .load(item.image)
                    .apply(RequestOptions().override(350, 300))
                    .into(itemView.findViewById(R.id.iv_recipe_image))
            tvRecipeTitle.setText(item.name)
            tvRecipeHeadline.setText(item.headline)
            tvCalories.setText("Calories : " + item.calories)
            tvCarbos.setText("Carbos : " + item.carbos)
            tvFats.setText("Fats : " + item.fats)
            tvProtein.setText("Protein : " + item.proteins)
            tvTime.setText("Time : " + item.time)
            tvDifficulty.setText("Difficulty : " + item.difficulty)
            tvDesc.setText(item.description)

            val maxTextViewWidth = 3
            val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(maxTextViewWidth, View.MeasureSpec.AT_MOST)
            val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            tvDesc.measure(widthMeasureSpec, heightMeasureSpec)
            val lineCount: Int = tvDesc.getLineCount()
            Log.d("LineCount", lineCount.toString())

            if (lineCount < 3){
                btnShowmore.visibility = View.GONE;
            } else {
                tvDesc.setMaxLines(3)
            }

            btnShowmore.setOnClickListener(View.OnClickListener {
                if (btnShowmore.getText().toString().equals("See More")) {
                    tvDesc.setMaxLines(Int.MAX_VALUE)
                    btnShowmore.setText("Show Less")
                } else {
                    tvDesc.setMaxLines(3) //your TextView
                    btnShowmore.setText("See More")
                }
            })
        }

        init {
            tvRecipeTitle = itemView.findViewById(R.id.tv_recipe_title)
            tvRecipeHeadline = itemView.findViewById(R.id.tv_recipe_headline)
            tvCalories = itemView.findViewById(R.id.tv_calories)
            tvCarbos = itemView.findViewById(R.id.tv_carbos)
            tvFats = itemView.findViewById(R.id.tv_fats)
            tvProtein = itemView.findViewById(R.id.tv_protein)
            tvTime = itemView.findViewById(R.id.tv_time)
            tvDifficulty = itemView.findViewById(R.id.tv_difficulty)
            tvDesc = itemView.findViewById(R.id.tv_recipe_description)
            btnShowmore = itemView.findViewById(R.id.btn_showmore)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        recipe.get(position).let {
            holder.bind(it)
            }
    }

    override fun getItemCount(): Int = recipe.size
}