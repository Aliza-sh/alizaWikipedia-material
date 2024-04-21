package com.aliza.alizawikipedia.adapter

import com.aliza.alizawikipedia.data.ItemPost
import java.text.FieldPosition

interface ItemEvents {
    fun onItemClicked( itemPost: ItemPost)
    fun onMenuItemClick(itemPost: ItemPost,position: Int)
}