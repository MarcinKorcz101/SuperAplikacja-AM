package com.example.am_144446_145276

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import org.json.JSONObject
import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logoutButton = view.findViewById<Button>(R.id.logout_button)
        val profileNameText = view.findViewById<TextView>(R.id.profile_name)
        val profile_created_atText = view.findViewById<TextView>(R.id.profile_created_at)
        val lichess_placeholderText = view.findViewById<TextView>(R.id.lichess_placeholder)
        val stats_placeholderText = view.findViewById<TextView>(R.id.stats_placeholder)
        val lichessInfoView = view.findViewById<LinearLayout>(R.id.lichessInfo)
        val sharedHelper = SharedPreferencesHelper(requireContext())
        val loggedUser = sharedHelper.getLoggedUser()


        profileNameText.text = loggedUser.getString("username")

        //CreatedAt
        val formater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val createDateText = loggedUser.getString("createDate")
        val parsedDate = LocalDateTime.parse(createDateText, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        profile_created_atText.text = "Account created: $formattedDate"

        //Lichess
        var lichessNick = loggedUser.getString("lichessNick")
        if (lichessNick == "null") {
            lichessNick = "No Lichess Account Attached"
        }
        lichess_placeholderText.text = lichessNick
        //TODO dodanie konta Lichess
        lichessInfoView.setOnClickListener(){
            println("dodaj konto")
        }
        logoutButton.setOnClickListener() {
            context?.let { SharedPreferencesHelper(it) }
                ?.putLoggedUser(JSONObject("""{username: ""}"""))
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment user_profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}