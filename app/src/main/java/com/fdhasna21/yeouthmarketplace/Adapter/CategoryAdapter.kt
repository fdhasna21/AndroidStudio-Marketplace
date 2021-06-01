package com.fdhasna21.yeouthmarketplace.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fdhasna21.yeouthmarketplace.CatalogDetail.CatalogActivity
import com.fdhasna21.yeouthmarketplace.DataClass.CategoryGroup
import com.fdhasna21.yeouthmarketplace.DataClass.CategoryMerchandise
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.emptyCategoriesGroup
import com.fdhasna21.yeouthmarketplace.emptyCategoriesMerchandise
import kotlinx.android.synthetic.main.item_category.view.*


//for each content of CategoryGroup and CategoryMerchandise
class CategoryAdapter(val categoryGroup : ArrayList<CategoryGroup> = emptyCategoriesGroup,
                      val categoryMerchandise: ArrayList<CategoryMerchandise> = emptyCategoriesMerchandise,
                      val context:Context)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameSubCategory = itemView.item_category_name
        val imageSubCategory = itemView.item_category_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO : image
        if(categoryMerchandise != emptyCategoriesMerchandise){
            val item = categoryMerchandise.get(position)
            holder.nameSubCategory.text = item.merchName
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemProduct")
                intent.putExtra("detailType", "merchandise")
                intent.putExtra("productType", arrayListOf<String>(item.merchName.toString(), item.merchID.toString()))
                context.startActivity(intent)
            }
        }
        else {//if(categoryGroup != emptyCategoriesGroup){
            val item = categoryGroup.get(position)
            holder.nameSubCategory.text = item.groupName
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, CatalogActivity::class.java)
                intent.putExtra("menuType", "itemProduct")
                intent.putExtra("detailType", "group")
                intent.putStringArrayListExtra("productType", arrayListOf(item.groupName.toString(), item.groupID.toString()))
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        if(categoryMerchandise != emptyCategoriesMerchandise) {
            return categoryMerchandise.size
        }
        else{
            return  categoryGroup.size
        }
    }
}