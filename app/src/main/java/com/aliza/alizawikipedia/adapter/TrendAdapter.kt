package com.aliza.alizawikipedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizawikipedia.R
import com.aliza.alizawikipedia.data.ItemPost
import com.aliza.alizawikipedia.databinding.ItemTrendBinding
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class TrendAdapter(val data: List<ItemPost>) :
    RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {
    lateinit var binding: ItemTrendBinding

    inner class TrendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemPost: ItemPost) {
            Glide
                .with(itemView.context)
                .load(itemPost.imgUrl)
                .transform(RoundedCornersTransformation(24, 8))
                .into(binding.imgItemTrend)
            binding.txtItemTrendTitle.text = itemPost.txtTitle
            binding.txtItemTrendSubtitle.text = itemPost.txtSubtitle
            binding.txtItemTrendInsight.text = itemPost.insight
            binding.txtItemTrendNumber.text = (adapterPosition + 1).toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        binding = ItemTrendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrendViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bindViews(data[position])
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                binding.root.context,
                R.anim.anim_recycler_item
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }
}