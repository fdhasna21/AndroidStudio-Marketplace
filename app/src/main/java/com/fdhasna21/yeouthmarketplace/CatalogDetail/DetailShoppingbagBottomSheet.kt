package com.fdhasna21.yeouthmarketplace.CatalogDetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fdhasna21.yeouthmarketplace.Orders.OrderActivity
import com.fdhasna21.yeouthmarketplace.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_detail_quantity.*

class DetailShoppingbagBottomSheet : BottomSheetDialogFragment(){
    var totalOrder:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.bottomsheet_detail_quantity, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun display(order:Int){
            bottomsheet_detail_txt_quantity.text = totalOrder.toString()
        }

        bottomsheet_detail_btn_nextAction.setOnClickListener{
            val intent = Intent(context, OrderActivity::class.java)
            // TODO : add to shoppingbag, send add shoppingbag
            startActivity(intent)
        }

        bottomsheet_detail_btn_decre.setOnClickListener {
            if(totalOrder == 0){totalOrder=0}
            else{totalOrder -= 1}
            display(totalOrder)
        }

        bottomsheet_detail_btn_incre.setOnClickListener {
            totalOrder+=1
            display(totalOrder)
        }
    }

}