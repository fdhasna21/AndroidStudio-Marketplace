package com.fdhasna21.yeouthmarketplace.SignInUp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdhasna21.yeouthmarketplace.All.clearFocusableAllEditText
import com.fdhasna21.yeouthmarketplace.All.removeResponseRegex
import com.fdhasna21.yeouthmarketplace.All.resetErrorEdittext
import com.fdhasna21.yeouthmarketplace.DataClass.UserInfo
import com.fdhasna21.yeouthmarketplace.R
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorHelper
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.ErrorResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ResponseDataClass.SuccessResponse
import com.fdhasna21.yeouthmarketplace.SettingsAPI.ServerAPI
import com.fdhasna21.yeouthmarketplace.SettingsAPI.Interface.UserInterface
import kotlinx.android.synthetic.main.activity_sign_in_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignUpFragment : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val input_name = view.signup_input_name
        val input_email = view.signup_input_email
        val input_password = view.signup_input_password
        val input_confirm = view.signup_input_passwordConfirm

        resetErrorEdittext(view.signup_layout_name, input_name)
        resetErrorEdittext(view.signup_layout_email, input_email)
        resetErrorEdittext(view.signup_layout_password, input_password)
        resetErrorEdittext(view.signup_layout_passwordConfirm, input_confirm)

        view.signup_btn_signUp.setOnClickListener {
            clearFocusableAllEditText(arrayListOf(input_name, input_email, input_password, input_confirm))
            view.signup_layout_name.error = null
            view.signup_layout_email.error = null
            view.signup_layout_password.error = null
            view.signup_layout_passwordConfirm.error = null

            val checkPassword : Boolean = input_password.text.toString() == input_confirm.text.toString()
            if(input_name.text.toString().isNotEmpty() &&
                input_email.text.toString().isNotEmpty() &&
                input_password.text.toString().isNotEmpty() &&
                input_confirm.text.toString().isNotEmpty() &&
                checkPassword) {

                val userInfo = UserInfo(userName = input_name.text.toString(),
                        userEmail = input_email.text.toString(),
                        userPassword = input_password.text.toString(),
                        userPasswordConfirmation = input_confirm.text.toString())

                val loadingBar = activity?.signinup_progress
                loadingBar!!.visibility = View.VISIBLE
                val userInterface : UserInterface = ServerAPI().getServerAPI()!!.create(UserInterface::class.java)
                userInterface.userRegis(userInfo).enqueue(object : Callback<SuccessResponse> {
                    override fun onResponse(call: Call<SuccessResponse>?, response: Response<SuccessResponse>?) {
                        if (response!!.isSuccessful) {
                            loadingBar!!.visibility = View.GONE
                            Toast.makeText(activity, "Registration successfull.", Toast.LENGTH_LONG).show()
                            nav_host_fragment.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                        } else {
                            loadingBar!!.visibility = View.GONE
                            try {
                                val output: ErrorResponse = ErrorHelper().parseErrorBody(response)
                                view.signup_layout_email.error =
                                        if (output.errors?.email.toString() != "null") {
                                            removeResponseRegex(output.errors?.email.toString())
                                        } else {
                                            null
                                        }
                            } catch (e: Exception) {
                            }
                        }
                    }

                    override fun onFailure(call: Call<SuccessResponse>?, t: Throwable) {
                        Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("failure", t.toString())
                    }
                })
            } else{
                if(input_name.text.toString().isEmpty()){
                    view.signup_layout_name.error = "Name cannot be null or empty."
                }
                if(input_email.text.toString().isEmpty()){
                    view.signup_layout_email.error = "Email cannot be null or empty."
                }
                if(input_password.text.toString().isEmpty()){
                    view.signup_layout_password.error = "Password cannot be null or empty."
                }
                if(input_confirm.text.toString().isEmpty()){
                    view.signup_layout_passwordConfirm.error = "Confirm password cannot be null or empty."
                }
                if(!checkPassword){
                    view.signup_layout_passwordConfirm.error = "Password and Confirm Password do not match."
                }
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}