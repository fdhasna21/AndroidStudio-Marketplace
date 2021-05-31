package com.fdhasna21.yeouthmarketplace.MainApplication

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fdhasna21.yeouthmarketplace.Adapter.FeedsAdapter
import com.fdhasna21.yeouthmarketplace.DataClass.Feed
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.CategoryFeedInterface
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorHelper
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import kotlinx.android.synthetic.main.fragment_main_feeds.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFeeds.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFeeds : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var loadingBar : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryFeedInterface = ServerAPI().getServerAPI()!!.create(CategoryFeedInterface::class.java)
        categoryFeedInterface.feedProduct(5).enqueue(object : Callback<Feed>{
            override fun onResponse(
                call: Call<Feed>?,
                response: Response<Feed>?
            ) {
                if(response!!.isSuccessful){
                    val feedsAdapter = FeedsAdapter(
                        arrayListOf(1,2,3),
                        response.body()?.newCollection,
                        response.body()?.trendingMerchandise,
                        response.body()?.bestSeller,
                        requireContext()
                    )

                    view.feeds_recycler_container.layoutManager = LinearLayoutManager(context)
                    view.feeds_recycler_container.adapter = feedsAdapter

//                    val newCollection = ProductAdapter(response.body()?.newCollection!!, requireContext())
//                    view.feeds_newCollection_container.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//                    view.feeds_newCollection_container.adapter = newCollection
//
//                    val trendingMerchandise = ProductAdapter(response.body()?.trendingMerchandise!!, requireContext())
//                    view.feeds_trendingMerchandise_container.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//                    view.feeds_trendingMerchandise_container.adapter = trendingMerchandise
//
//                    val bestSeller = ProductAdapter(response.body()?.bestSeller!!, requireContext())
//                    view.feeds_bestSeller_container.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//                    view.feeds_bestSeller_container.adapter = bestSeller
                }else {
                    try {
                        Toast.makeText(activity, "Failed to load data.", Toast.LENGTH_SHORT).show()
                        val output: ErrorResponse = ErrorHelper().parseErrorBody(response!!)
                        Toast.makeText(activity, output.toString(), Toast.LENGTH_LONG).show()
                    } catch (e: Exception) { }
                }

            }

            override fun onFailure(call: Call<Feed>?, t: Throwable) {
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
         * @return A new instance of fragment MainFeeds.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFeeds().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}