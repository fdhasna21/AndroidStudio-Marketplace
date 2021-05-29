package com.fdhasna21.yeouthmarketplace.MainApplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fdhasna21.yeouthmarketplace.DataClass.Category
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.CategoryFeedInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.UserInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.fdhasna21.yeouthmarketplace.apiToken
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        /* TOP BAR : topbar_mainmenu*/
        mainTopBar = findViewById(R.id.main_topbar_menu)
        val searchEditText = findViewById<EditText>(R.id.main_topbar_edittext_search)
        val searchIcon = mainTopBar.menu.findItem(R.id.topbar_search)
        searchIcon.setVisible(false)
        mainTopBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.topbar_shoppingbag -> {
//                    val intent = Intent(applicationContext, ShoppingBag::class.java)
//                    startActivity(intent)
                    Toast.makeText(this, "Shopping Bag", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        /* BOTTOM BAR : bottombar_mainmenu */
        //TODO : make mainBottomBar always hide when softkey popup
        mainBottomBar = findViewById(R.id.main_bottombar_menu)
        setCurrentFragment(MainHome())
        mainBottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottombar_home     -> {
                    setCurrentFragment(MainHome())
                    searchEditText.visibility = View.VISIBLE
                    searchEditText.text = null
                    searchIcon.setVisible(false)
                    mainTopBar.title = ""
                }
                R.id.bottombar_feeds -> {
                    setCurrentFragment(MainFeeds())
                    searchEditText.visibility = View.INVISIBLE
                    searchIcon.setVisible(true)
                    searchIcon.collapseActionView()
                    mainTopBar.title = "Feeds"
                }
                R.id.bottombar_profile -> {
                    setCurrentFragment(MainProfile())
                    searchEditText.visibility = View.INVISIBLE
                    searchIcon.collapseActionView()
                    searchIcon.setVisible(true)
                    mainTopBar.title = "Profile"
                }
            }
            true
        }
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