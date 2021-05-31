package com.fdhasna21.yeouthmarketplace.CatalogDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.fdhasna21.yeouthmarketplace.Adapter.CategoryAdapter
import com.fdhasna21.yeouthmarketplace.Adapter.ProductCatalogAdapter
import com.fdhasna21.yeouthmarketplace.DataClass.Category
import com.fdhasna21.yeouthmarketplace.Orders.ShoppingbagActivity
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.CategoryFeedInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.ProductInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorHelper
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.fdhasna21.yeouthmarketplace.emptyCategoriesGroup
import com.fdhasna21.yeouthmarketplace.emptyCategoriesMerchandise
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_catalog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatalogActivity : AppCompatActivity() {
    private lateinit var catalogTopBar : MaterialToolbar
    private lateinit var drawerLayout_catalog : DrawerLayout
    private lateinit var actionBarToggle_catalog : ActionBarDrawerToggle
    private var topBarTitle : String = ""

    private fun itemCategory(by: String?){
        //by = "group", "merchandise"
        topBarTitle = by!!.capitalize()
        var categoryMerchandise = emptyCategoriesMerchandise
        var categoryGroup = emptyCategoriesGroup
        val categoryFeedInterface = ServerAPI().getServerAPI()!!.create(CategoryFeedInterface::class.java)
        categoryFeedInterface.categoryProduct(by, null).enqueue(object : Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful){
                    if(by == "group"){
                        categoryGroup = response.body()?.group!!
                    } else if(by == "merchandise"){
                        categoryMerchandise = response.body()?.merch!!
                    }
                    catalog_container.adapter = CategoryAdapter(categoryGroup, categoryMerchandise, this@CatalogActivity)
                } else {
                    try {
                        Toast.makeText(this@CatalogActivity, "Failed to load data.", Toast.LENGTH_SHORT).show()
                        val output: ErrorResponse = ErrorHelper().parseErrorBody(response)
                        //Toast.makeText(activity, output.toString(), Toast.LENGTH_LONG).show()
                        //test.text = "$apiToken ${output.toString()}"
                    } catch (e: Exception) { }
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@CatalogActivity, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("failure", t.toString())
            }
        })
    }


    private fun itemProduct(by:String?, detail:Array<String>?){
        topBarTitle = by!! //TODO : capitalize for feeds (remove "feeds")
        val productInterface = ServerAPI().getServerAPI()!!.create(ProductInterface::class.java)
        val query = when(by){
            "group" -> productInterface.allProducts(by, null, detail!![1].toInt(), null)
            "merchandise" -> productInterface.allProducts(by, detail!![1].toInt(), null, null)
            "search" -> productInterface.allProducts(by, null, null, detail!![1])
            else -> productInterface.allProducts(by,null,null,null)
        }
        query.enqueue(object : Callback<SuccessResponse>{
            override fun onResponse(
                call: Call<SuccessResponse>,
                response: Response<SuccessResponse>
            ) {
                if(response.isSuccessful){
                    catalog_container.adapter = ProductCatalogAdapter(response.body()?.products!!, this@CatalogActivity)
                } else {
                    try {
                        Toast.makeText(this@CatalogActivity, "Failed to load data.", Toast.LENGTH_SHORT).show()
                        val output: ErrorResponse = ErrorHelper().parseErrorBody(response)
                        //Toast.makeText(activity, output.toString(), Toast.LENGTH_LONG).show()
                        //test.text = "$apiToken ${output.toString()}"
                    } catch (e: Exception) { }
                }
            }

            override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                Toast.makeText(this@CatalogActivity, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("failure", t.toString())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        /* ADAPTER & TOP BAR TITLE */
        val menuType = intent.getStringExtra("menuType") //itemCategory or itemProduct
        val detailType = intent.getStringExtra("detailType") // group/merch feedsNewCollection etc/all/search
        val productType = intent.getStringArrayExtra("productType") //detailType and its param (group, merchandise, product, and search)
        catalog_container.layoutManager = GridLayoutManager(this,2)
        if(menuType == "itemCategory"){
            itemCategory(detailType)
        } else{
            itemProduct(detailType, productType)
        }

        /* DRAWER LAYOUT */
        drawerLayout_catalog = findViewById(R.id.catalog_drawer_layout)
        actionBarToggle_catalog = ActionBarDrawerToggle(this, drawerLayout_catalog, 0,0)
        drawerLayout_catalog.addDrawerListener(actionBarToggle_catalog)
        actionBarToggle_catalog.syncState()

        /* TOP BAR */
        catalogTopBar = findViewById(R.id.catalog_topbar_menu)
        catalogTopBar.title = topBarTitle
        setSupportActionBar(catalogTopBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_topbar_catalog, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) { //TODO : search
            R.id.topbar_filter -> {
                if(this.drawerLayout_catalog.isDrawerOpen(GravityCompat.END)){
                    this.drawerLayout_catalog.closeDrawer(GravityCompat.END)
                }
                else{
                    this.drawerLayout_catalog.openDrawer(GravityCompat.END)
                }
            }
            R.id.topbar_shoppingbag -> {
                val intent = Intent(applicationContext, ShoppingbagActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}