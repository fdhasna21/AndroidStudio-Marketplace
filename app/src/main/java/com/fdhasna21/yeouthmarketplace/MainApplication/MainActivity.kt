package com.fdhasna21.yeouthmarketplace.MainApplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fdhasna21.yeouthmarketplace.Orders.OrderActivity
import com.fdhasna21.yeouthmarketplace.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mainBottomBar : BottomNavigationView
    private lateinit var mainTopBar : MaterialToolbar

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.main_fragment_container, fragment)
        commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO : change using navigation

        /* TOP BAR : topbar_mainmenu*/
        mainTopBar = findViewById(R.id.main_topbar_menu)
        val searchEditText = findViewById<EditText>(R.id.main_topbar_edittext)
        val searchIcon = mainTopBar.menu.findItem(R.id.topbar_search)
        searchIcon.setVisible(false)
        mainTopBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.topbar_shoppingbag -> {
                    val intent = Intent(applicationContext, OrderActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        /* BOTTOM BAR : bottombar_mainmenu */
        mainBottomBar = findViewById(R.id.main_bottombar_menu)
        setCurrentFragment(MainHome())
        mainBottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottombar_home     -> {
                    setCurrentFragment(MainHome())
                    searchEditText.text = null
                    searchEditText.visibility = View.VISIBLE
                    searchIcon.setVisible(false)
                    mainTopBar.title = ""
                }
                R.id.bottombar_feeds -> {
                    setCurrentFragment(MainFeeds())
                    searchEditText.visibility = View.GONE
                    searchIcon.setVisible(true)
                    searchIcon.collapseActionView()
                    mainTopBar.title = "Feeds"
                }
                R.id.bottombar_profile -> {
                    setCurrentFragment(MainProfile())
                    searchEditText.visibility = View.GONE
                    searchIcon.collapseActionView()
                    searchIcon.setVisible(true)
                    mainTopBar.title = "Profile"
                }
            }
            true
        }

//        /* LOADING BAR : main_progress */
//        MainHome().loadingBar = main_progress
//        MainFeeds().loadingBar = main_progress
//        MainProfile().loadingBar = main_progress
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}