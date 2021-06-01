package com.fdhasna21.yeouthmarketplace.Orders

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GravityCompat
import com.fdhasna21.yeouthmarketplace.R
import com.google.android.material.appbar.MaterialToolbar

class OrderActivity : AppCompatActivity() {
    private lateinit var orderTopBar : MaterialToolbar
    private var topBarTitle : String = "Order"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        //TODO : add ShoppingbagFragment and CheckoutFragment

        /* TOP BAR */
        orderTopBar = findViewById(R.id.order_topbar_menu)
        orderTopBar.title = topBarTitle
        setSupportActionBar(orderTopBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_topbar_order, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) { //TODO : topbar order
//        }
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
        //TODO : if from detail, back to detail (not its qty bottombar)
        onBackPressed()
        return true
    }
}