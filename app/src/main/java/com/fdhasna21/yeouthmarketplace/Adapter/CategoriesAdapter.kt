package com.fdhasna21.yeouthmarketplace.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fdhasna21.yeouthmarketplace.CatalogDetail.CatalogActivity
import com.fdhasna21.yeouthmarketplace.DataClass.CategoryGroup
import com.fdhasna21.yeouthmarketplace.DataClass.CategoryMerchandise
import com.fdhasna21.yeouthmarketplace.R
import kotlinx.android.synthetic.main.item_categories_catalog.view.*
import java.util.ArrayList


class CategoriesAdapter(val categoryArray : ArrayList<Int>,
                        val categoryMerchandise: ArrayList<CategoryMerchandise>?,
                        val categoryGroup : ArrayList<CategoryGroup>?,
                        val context: Context)
    : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameCategory = itemView.home_group_name
        val seeAllCategory = itemView.home_group_seeAll
        val containerCategory = itemView.home_group_container
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_categories_catalog, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0){
            holder.nameCategory.text = "Merchandise"
            holder.containerCategory.adapter = CategoryAdapter(categoryMerchandise = categoryMerchandise!!, context = holder.containerCategory.context)
            holder.containerCategory.layoutManager = LinearLayoutManager(holder.containerCategory.context, RecyclerView.HORIZONTAL, false)
            holder.seeAllCategory.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemCategory")
                intent.putExtra("detailType", "merchandise")
                context.startActivity(intent)
            }
        }
        else{
            holder.nameCategory.text = "Group"
            holder.containerCategory.adapter = CategoryAdapter(categoryGroup = categoryGroup!!, context = holder.containerCategory.context)
            holder.containerCategory.layoutManager = LinearLayoutManager(holder.containerCategory.context, RecyclerView.HORIZONTAL, false)
            holder.seeAllCategory.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemCategory")
                intent.putExtra("detailType", "group")
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryArray.size
    }
}