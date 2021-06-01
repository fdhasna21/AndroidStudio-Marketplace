package com.fdhasna21.yeouthmarketplace.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fdhasna21.yeouthmarketplace.CatalogDetail.CatalogActivity
import com.fdhasna21.yeouthmarketplace.DataClass.MainProduct
import com.fdhasna21.yeouthmarketplace.R
import kotlinx.android.synthetic.main.item_feeds_catalog.view.*

class FeedsAdapter(val feedsArray : ArrayList<Int>,
                   val feedsNewCollection : ArrayList<MainProduct>?,
                   val feedsTrendingMerchandise : ArrayList<MainProduct>?,
                   val feedsBestSeller : ArrayList<MainProduct>?,
                   val context: Context)
    : RecyclerView.Adapter<FeedsAdapter.ViewHolder>(){
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameFeeds = itemView.item_feeds_name
        val seeAllFeeds = itemView.item_feeds_seeAll
        val containerFeeds = itemView.item_feeds_container
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_feeds_catalog, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0){
            holder.nameFeeds.text = "New Collection"
            holder.containerFeeds.adapter = ProductCatalogAdapter(feedsNewCollection!!, holder.containerFeeds.context)
            holder.containerFeeds.layoutManager = LinearLayoutManager(holder.containerFeeds.context, RecyclerView.HORIZONTAL, false)
            holder.seeAllFeeds.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemProduct")
                intent.putExtra("detailType", "feedsNewCollection")
                intent.putStringArrayListExtra("productType", arrayListOf<String>("New Collection", "0"))
                context.startActivity(intent)
            }
        }
        else if(position == 1){
            holder.nameFeeds.text = "Trending Merchandise"
            holder.containerFeeds.adapter = ProductCatalogAdapter(feedsTrendingMerchandise!!, holder.containerFeeds.context)
            holder.containerFeeds.layoutManager = LinearLayoutManager(holder.containerFeeds.context, RecyclerView.HORIZONTAL, false)
            holder.seeAllFeeds.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemProduct")
                intent.putExtra("detailType", "feedsTrendingMerchandise")
                intent.putStringArrayListExtra("productType", arrayListOf<String>("Trending Merchandise", "0"))
                context.startActivity(intent)
            }
        }
        else{
            holder.nameFeeds.text = "Best Seller"
            holder.containerFeeds.adapter = ProductCatalogAdapter(feedsBestSeller!!, holder.containerFeeds.context)
            holder.containerFeeds.layoutManager = LinearLayoutManager(holder.containerFeeds.context, RecyclerView.HORIZONTAL, false)
            holder.seeAllFeeds.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemProduct")
                intent.putExtra("detailType", "feedsBestSeller")
                intent.putStringArrayListExtra("productType", arrayListOf<String>("Best Seller", "0"))
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return feedsArray.size
    }
}