package com.aliza.alizawikipedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizawikipedia.R
import com.aliza.alizawikipedia.data.ItemPost
import com.aliza.alizawikipedia.databinding.ItemExploreBinding
import com.bumptech.glide.Glide

class ExploreAdapter(val data: List<ItemPost>) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {
    lateinit var binding: ItemExploreBinding

    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemPost: ItemPost) {
            Glide
                .with(itemView.context)
                .load(itemPost.imgUrl)
                .into(binding.imgItemExplore)
            binding.txtItemExploreTitle.text = itemPost.txtTitle
            binding.txtItemExploreSubtitle.text = itemPost.txtSubtitle
            binding.txtItemExploreDetail.text = itemPost.txtDetail

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        binding = ItemExploreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExploreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bindViews(data[position])
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(binding.root.context,
            R.anim.anim_recycler_item
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}