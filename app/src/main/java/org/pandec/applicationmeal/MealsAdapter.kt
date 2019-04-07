package org.pandec.applicationmeal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_list_meal.view.*

class MealsAdapter(private val context: Context, private val list: List<MealsItem>, private val listener: (MealsItem) -> Unit)
    : RecyclerView.Adapter<MealsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_list_meal, p0,
                false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(list[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(mealsItem: MealsItem, listener: (MealsItem) -> Unit) {
            itemView.tvTitle.text = mealsItem.strMeal
            Log.d("Image", mealsItem.strMealThumb)
            Picasso.get()
                .load(mealsItem.strMealThumb)
                .into(itemView.ivImage)

            itemView.setOnClickListener {
                listener(mealsItem)
            }
        }
    }
}