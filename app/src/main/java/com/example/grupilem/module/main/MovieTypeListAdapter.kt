package com.example.grupilem.module.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grupilem.MovieType
import com.example.grupilem.R
import kotlinx.android.synthetic.main.item_movie_type.view.*

class MovieTypeListAdapter(
    private val data: MutableList<MovieType> = mutableListOf()
): RecyclerView.Adapter<MovieTypeListAdapter.MovieTypeViewHolder>() {

    private var listener: MovieTypeListener? = null

    inner class MovieTypeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieType) {
            itemView.movieTypeTextView.text = item.value
            itemView.setOnClickListener {
                listener?.onMovieTypeClicked(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_type, parent, false)
        return MovieTypeViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MovieTypeViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int  = data.size

    fun replaceData(newData: List<MovieType>) {
        if (data.isNotEmpty()) {
            data.clear()
        }
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun setListener(newListener: MovieTypeListener) {
        this.listener = newListener
    }

    interface MovieTypeListener {
        fun onMovieTypeClicked(id: Int)
    }
}