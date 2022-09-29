package com.example.am_144446_145276

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.am_144446_145276.helpers.RestHelper
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddLichessAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddLichessAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val restHelper = RestHelper()
    lateinit var userJson : JSONObject
    private lateinit var lichessNick: String

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_lichess_account, container, false)
        val sharedHelper = SharedPreferencesHelper(requireContext())
        val loggedUser = sharedHelper.getLoggedUser()
        val addbtn = view.findViewById<Button>(R.id.nickname_lichess_account)
        val lichessnickname = view.findViewById<TextInputEditText>(R.id.lichess_nickname_input)
        addbtn.setOnClickListener() {
            val nick = lichessnickname.text.toString()
            var errorAPI = false
            lateinit var user: JSONArray

            if (nick == "") {
                Toast.makeText(
                    requireContext(),
                    "Username can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Thread(){
                run {
                    try {
                        userJson = restHelper.getLichessUserInfo(nick)
                    }catch (e: IOException){
                        errorAPI = true
                    }finally {
                        restHelper.updateUser(loggedUser.getString("username"), nick)
                        sharedHelper.putLichessAccount(nick)
                    }
                }
                activity?.runOnUiThread {
                    //lichess
                    if (!errorAPI){
                        Navigation.findNavController(view).navigate(R.id.action_addLichessAccountFragment_to_userProfileFragment)
                    }else{
                        Toast.makeText(
                            requireContext(),
                            "User not exists",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }.start()
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
         * @return A new instance of fragment AddLichessAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddLichessAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}