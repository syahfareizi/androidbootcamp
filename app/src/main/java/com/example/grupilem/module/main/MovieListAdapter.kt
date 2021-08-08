package com.example.grupilem.module.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.grupilem.R
import com.example.grupilem.data.remote.ApiEndpoint
import com.example.grupilem.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(
    private val data: MutableList<Movie> = mutableListOf()
): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var listener: MovieListListener? = null

    inner class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie) {
            Glide.with(itemView.context).load(ApiEndpoint.BASE_IMAGE_URL + item.posterPath)
                .into(itemView.itemMovieImageView)
            itemView.itemMovieTextView.text = item.title
            itemView.itemMovieRatingTextView.text = item.voteAverage.toString()
            itemView.setOnClickListener {
                listener?.onMovieClicked(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    fun replaceData(newData: List<Movie>) {
        if (data.isNotEmpty()) {
            data.clear()
        }
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun setListener(newListener: MovieListListener) {
        this.listener = newListener
    }

    interface MovieListListener {
        fun onMovieClicked(id: Int)
    }
}