package com.fdhasna21.yeouthmarketplace.All

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

fun getCurrency(price: Int?):String{
    return NumberFormat.getCurrencyInstance(Locale("in", "id")).format(price)
}

fun removeResponseRegex(data : String?):String{
    return data!!.replace("[", "").replace("]","")
}

fun resetErrorEdittext(editTextInputLayout: TextInputLayout, editText: TextInputEditText){
    editText.addTextChangedListener{
        editTextInputLayout.error = null
        editTextInputLayout.focusable = View.NOT_FOCUSABLE
    }
}

fun clearFocusableAllEditText(editText: ArrayList<TextInputEditText>){
    editText.forEach { it.clearFocus() }
}