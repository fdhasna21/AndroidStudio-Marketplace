package com.fdhasna21.yeouthmarketplace.CatalogDetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.fdhasna21.yeouthmarketplace.Orders.OrderActivity
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.ProductInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorHelper
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.bottombar_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var detailTopBar : MaterialToolbar
    private var topBarTitle : String = "Product Detail"

    private var productID : Int = 0
    private lateinit var productName : String
    private lateinit var productCategory : String
    private lateinit var productDetail : String
    private lateinit var productRelease : Date
    private var productSold : Int = 0
    private var productRate : Double = 0.00
    private var productWishlisted : Int = 0
    private var productsImages = ArrayList<String>()
    private var productsPrice = ArrayList<Int>()
    private var productsStok = ArrayList<Int>()

    fun displayData(){
        detail_product_name.text = productName
        detail_product_category.text = productCategory
        detail_product_desc.text = productDetail
        detail_product_release.text = productRelease.toString()
        detail_product_sold.text = productSold.toString()
        detail_product_rate.text = productRate.toString()
        detail_product_wishlisted.text = productWishlisted.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        detail_progress.visibility = View.VISIBLE
        productID = intent.getIntExtra("productID", 0)
        val productInterface = ServerAPI().getServerAPI()!!.create(ProductInterface::class.java)
        productInterface.eachProduct(productID).enqueue(object : Callback<SuccessResponse>{
            override fun onResponse(
                call: Call<SuccessResponse>,
                response: Response<SuccessResponse>
            ) {
                if(response.isSuccessful){
                    //TODO : add data to each view (product)
                    detail_progress.visibility = View.GONE
                    val output = response.body()!!.product
                    productName = output?.productName!!
                    productCategory = output.productCategory!!
                    productDetail = output.productDetail!!
                    productRelease = output.productRelease!!
                    productSold = output.productSold!!
                    productRate = output.productRate!!
                    productWishlisted = output.productWishlisted!!
                    for(version in output.productVersion!!){
                        //TODO : productsImages.add(version.versionImage.toString())
                        productsPrice.add(version.versionPrice!!)
                        productsStok.add(version.versionStock!!)
                    }
                    displayData()
                    //Toast.makeText(this@ProductDetailActivity, output.toString(), Toast.LENGTH_LONG).show()
                }else {
                    detail_progress.visibility = View.GONE
                    try {
                        //Toast.makeText(this@ProductDetailActivity, "Failed to load data.", Toast.LENGTH_SHORT).show()
                        val output: ErrorResponse = ErrorHelper().parseErrorBody(response)
                        Toast.makeText(this@ProductDetailActivity, output.toString(), Toast.LENGTH_LONG).show()
                        //test.text = "$apiToken ${output.toString()}"
                    } catch (e: Exception) { }
                }
            }

            override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                Toast.makeText(this@ProductDetailActivity, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("failure", t.toString())
            }

        })

        /* TOP BAR */
        detailTopBar = findViewById(R.id.detail_topbar_menu)
        detailTopBar.title = topBarTitle
        setSupportActionBar(detailTopBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        /* BOTTOM BAR */
        bottombar_detail_checkout.setOnClickListener {
            val intent = Intent(applicationContext, OrderActivity::class.java)
            // TODO : add to shoppingbag, send add shoppingbag (qty 1)
            startActivity(intent)
        }

        bottombar_detail_shoppingbag.setOnClickListener {
            // TODO : send product_variation for stock
            val btn_bottomsheet = DetailShoppingbagBottomSheet()
            btn_bottomsheet.show(supportFragmentManager, "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_topbar_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) { //TODO : wishlist check if wishlisted or not. if already added, full one icon
            R.id.topbar_wishlist -> Toast.makeText(this, "Wishlist", Toast.LENGTH_SHORT).show()
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