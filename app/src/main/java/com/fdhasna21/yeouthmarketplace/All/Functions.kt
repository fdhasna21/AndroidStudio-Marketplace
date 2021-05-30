package com.fdhasna21.yeouthmarketplace.All

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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