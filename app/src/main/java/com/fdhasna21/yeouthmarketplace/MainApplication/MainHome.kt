package com.fdhasna21.yeouthmarketplace.MainApplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fdhasna21.yeouthmarketplace.DataClass.Category
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.CategoryFeedInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorHelper
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.fdhasna21.yeouthmarketplace.apiToken
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_home.*
import kotlinx.android.synthetic.main.fragment_main_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHome : Fragment() {
    var sampleImages = intArrayOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var imageListener = ImageListener { position, imageView -> imageView.setImageResource(sampleImages[position]) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ):
            View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carouselView = requireView().findViewById(R.id.home_carousel) as CarouselView
        carouselView.setImageListener(imageListener)
        carouselView.setPageCount(sampleImages.size)

//        val itemAdapter = ProductCategoriesAdapter(arrayCategories, requireActivity())
//        productcategories_container.layoutManager = LinearLayoutManager(activity)
//        productcategories_container.adapter = itemAdapter

        //TODO : make adapter
        val categoryFeedInterface = ServerAPI().getServerAPI()!!.create(CategoryFeedInterface::class.java)
        categoryFeedInterface.categoryProduct(null, 5).enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>?, response: Response<Category>?) =
                if (response!!.isSuccessful) {
                    Toast.makeText(activity, response.body().toString(), Toast.LENGTH_LONG).show()
                } else {
                    try {
                        Toast.makeText(activity, "Failed to load data.", Toast.LENGTH_SHORT).show()
                        val output: ErrorResponse = ErrorHelper().parseErrorBody(response)
                        //Toast.makeText(activity, output.toString(), Toast.LENGTH_LONG).show()
                        //test.text = "$apiToken ${output.toString()}"
                    } catch (e: Exception) { }
                }

            override fun onFailure(call: Call<Category>?, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("failure", t.toString())
            }
           })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHome.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}