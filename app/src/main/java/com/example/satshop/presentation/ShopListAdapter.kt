package com.example.satshop.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.satshop.R
import com.example.satshop.data.ShopListRepositoryImpl.getShopItem
import com.example.satshop.domain.ShopItem

class ShopListAdapter : androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener: ((ShopItem)-> Unit)? = null
    var onShopItemClickListener: ((ShopItem)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val typeItemShopHolder = when (viewType) {
            VIEW_TYPE_ENABLE -> R.layout.item_shop_enable
            VIEW_TYPE_DISABLE -> R.layout.item_shop_disable
            else -> throw RuntimeException("Unknown view type: $viewType")
        }


        val view = LayoutInflater.from(parent.context).inflate(
            typeItemShopHolder,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getShopItem(position)
        return if (shopItem.enabled)
            VIEW_TYPE_ENABLE
        else VIEW_TYPE_DISABLE

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getShopItem(position)

        holder.tvCount.text = shopItem.count.toString()
        holder.tvName.text = shopItem.name
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

    }



    companion object {
        const val VIEW_TYPE_ENABLE = 0
        const val VIEW_TYPE_DISABLE = 1
        const val MAX_POOL_SIZE = 30
    }


}