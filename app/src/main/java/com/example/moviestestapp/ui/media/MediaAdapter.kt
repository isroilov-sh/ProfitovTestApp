package com.example.moviestestapp.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestestapp.R
import com.example.moviestestapp.data.Result

class MediaAdapter(private val data: List<Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder =
        MediaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_media, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MediaViewHolder).apply {
            bind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}