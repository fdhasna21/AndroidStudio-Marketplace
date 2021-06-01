package com.fdhasna21.yeouthmarketplace.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fdhasna21.yeouthmarketplace.All.getCurrency
import com.fdhasna21.yeouthmarketplace.CatalogDetail.CatalogActivity
import com.fdhasna21.yeouthmarketplace.CatalogDetail.ProductDetailActivity
import com.fdhasna21.yeouthmarketplace.DataClass.MainProduct
import com.fdhasna21.yeouthmarketplace.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductCatalogAdapter(val productArray : ArrayList<MainProduct>, val context: Context)
    : RecyclerView.Adapter<ProductCatalogAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameProduct = itemView.item_product_name
        val categoryProduct = itemView.item_product_category
        val priceProduct = itemView.item_product_price
        val rateProduct = itemView.item_product_rate
        val soldProduct = itemView.item_product_sold
        val imageProduct = itemView.item_product_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO : image
        val item = productArray.get(position)
        holder.nameProduct.text = item.productName
        holder.categoryProduct.text = item.productCategory
        holder.priceProduct.text = getCurrency(item.productVersion?.get(0)?.versionPrice)
        holder.rateProduct.text = item.productRate.toString()
        holder.soldProduct.text = item.productSold.toString()
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("productID", item.productID)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productArray.size
    }
}