package com.example.moviestestapp.ui.media

import android.view.View
import com.bumptech.glide.Glide
import com.example.moviestestapp.R
import com.example.moviestestapp.data.Result
import com.example.moviestestapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.recycler_item_media.view.*

class MediaViewHolder(itemView: View) : BaseViewHolder<Result>(itemView) {
    override fun bind(item: Result) {
        with(itemView) {
            tvTitleItemMedia.text = item.headline
            ivMainItemMedia.setImageResource(R.drawable.placeholder_no_image)
            item.multimedia?.src?.let {
                Glide.with(context)
                    .load(it)
                    .error(R.drawable.placeholder_no_image)
                    .into(ivMainItemMedia)
            }
        }
    }
}