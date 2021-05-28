package com.fdhasna21.yeouthmarketplace.SignInUp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdhasna21.yeouthmarketplace.DataClass.UserInfo
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.fdhasna21.yeouthmarketplace.SettingsAPI.UserInterface
import kotlinx.android.synthetic.main.activity_sign_in_up.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val input_email = view.signin_input_email
        val input_password = view.signin_input_password
        val txt_signUp = view.signin_txtClick_signUp
        val btn_signIn = view.signin_btn_signIn

        txt_signUp.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        btn_signIn.setOnClickListener {
            //TODO : reduce space of error, reset error (clear focusable etc)
            view.signin_layout_email.error = null
            view.signin_layout_password.error = null
            if(!input_email.text.toString().isEmpty() && !input_password.text.toString().isEmpty()){
                val userAuth = UserInfo(
                    userEmail = input_email.text.toString(),
                    userPassword = input_password.text.toString()
                )

                val userInterface : UserInterface = ServerAPI().getServerAPI()!!.create(
                    UserInterface::class.java
                )
                userInterface.userLogin(userAuth).enqueue(object : Callback<UserInfo> {
                    override fun onResponse(call: Call<UserInfo>?, response: Response<UserInfo>?) {
                        if (response!!.isSuccessful) {
                            val apiToken = response.body()?.userAPItoken
                            //TODO : next activity
                        }
                        else {
                            try{
                                val jObjError = JSONObject(response.errorBody()!!.string())
                                val emailError = jObjError.getJSONObject("errors").getString("email")
                                val passworError = jObjError.getJSONObject("errors").getString("password")
                                Toast.makeText(context, jObjError.getJSONObject("errors").getString("email"), Toast.LENGTH_LONG).show()
                            } catch (e : Exception) {
                                Toast.makeText(getContext(), e.message, Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    override fun onFailure(call: Call<UserInfo>?, t: Throwable) {
                        Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("failure", t.toString())
                    }

                })
            }
            else{
                if(input_email.text.toString().isEmpty()){
                    view.signin_layout_email.error = "Email cannot be null or empty."
                }
                if(input_password.text.toString().isEmpty()){
                    view.signin_layout_password.error = "Password cannot be null or empty."
                }
            }
        }



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}