package com.example.satshop.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.satshop.R
import com.example.satshop.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : Adapter<ShopListAdapter.ShopItemViewHolder>() {
    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView= view.findViewById(R.id.tv_count)
    }
    var count = 0
    var shopList = listOf<ShopItem>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("count1","${++count}")
        val typeItemShopHolder = when(viewType){
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
        val shopItem = shopList[position]
        return if (shopItem.enabled)
            VIEW_TYPE_ENABLE
        else VIEW_TYPE_DISABLE

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]

        holder.tvCount.text = shopItem.count.toString()
        holder.tvName.text =shopItem.name
        holder.view.setOnLongClickListener { true }

    }

    override fun getItemCount(): Int {
        return shopList.size
    }
    companion object{
        const val VIEW_TYPE_ENABLE = 0
        const val VIEW_TYPE_DISABLE = 1
        const val MAX_POOL_SIZE = 30
    }
}