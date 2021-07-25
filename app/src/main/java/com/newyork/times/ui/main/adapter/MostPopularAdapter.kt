package com.newyork.times.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.newyork.times.R
import com.newyork.times.databinding.ListItemBinding
import com.newyork.times.model.Article

class MostPopularAdapter (private val clickListener: OnClickListener) :
    ListAdapter<Article, MostPopularAdapter.MostPopularViewHolder>(ArticleItemDiffCallback()) {

    class MostPopularViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, clickListener: OnClickListener) {
            binding.article = article
            binding.clickListener = clickListener
            val mediaList = article.mediaList
            if (mediaList.isNotEmpty()) {
                val mediaMetadataList = mediaList[0].mediaMetadataList
                if (mediaMetadataList.isNotEmpty()) {
                    val thumbnailImgUrl = mediaMetadataList[2].url
                    binding.ivThumbnail.load(thumbnailImgUrl) {
                        placeholder(R.drawable.loading_img)
                        error(R.drawable.ic_broken_image)
                    }
                }
            } else {
                binding.ivThumbnail.load(R.drawable.ic_broken_image)
            }
            binding.tvDate.text = article.publishedDate
            binding.executePendingBindings()
        }
    }

    interface OnClickListener {
        fun onClick(article: Article)
    }

    class ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.id == newItem.id

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MostPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}