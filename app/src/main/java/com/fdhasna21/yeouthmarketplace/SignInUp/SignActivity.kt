package com.fdhasna21.yeouthmarketplace.SignInUp

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdhasna21.yeouthmarketplace.R
import kotlinx.android.synthetic.main.activity_sign_in_up.*
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignActivity : AppCompatActivity() {
    fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

    fun displayBackButton(status : Boolean){
//        when(status){
//            true ->{signinup_btn_back.visibility = View.VISIBLE}
//            false->{signinup_btn_back.visibility = View.INVISIBLE}
//        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)

        //TODO : if apiToken is defined, goto MainActivity (use SQLite to save apiToken)

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