package com.aliza.alizawikipedia.adapter

import com.aliza.alizawikipedia.data.ItemPost

interface ItemEvents {
    fun onItemClicked( itemPost: ItemPost)
}