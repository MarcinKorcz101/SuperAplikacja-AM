package com.example.am_144446_145276

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.am_144446_145276.data.Meeting
import com.example.am_144446_145276.helpers.RestHelper
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import org.json.JSONObject
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

    private val restHelper = RestHelper()
    private lateinit var adapter: MyMeetingsAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var userJson : JSONObject
    var meetings : ArrayList<Meeting> = ArrayList<Meeting>()
    private lateinit var lichessNick: String
    var gamesWon : ArrayList<Meeting> = ArrayList<Meeting>()
    var gamesLost : ArrayList<Meeting> = ArrayList<Meeting>()
    var gamesTied : ArrayList<Meeting> = ArrayList<Meeting>()

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
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recyclerViewProfile)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyMeetingsAdapter(meetings)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyMeetingsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {}})
        profileNameText.text = loggedUser.getString("username")

        //CreatedAt
        val formater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val createDateText = loggedUser.getString("createDate")
        val parsedDate = LocalDateTime.parse(createDateText, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        profile_created_atText.text = "Account created: $formattedDate"
        Thread(){
            run {
                val username = loggedUser.getString("username")
                meetings = restHelper.getResolvedGamesUser(username)
                gamesWon = restHelper.getAllGamesWonByUser(username)
                gamesLost = restHelper.getAllGamesLostByUser(username)
                gamesTied = restHelper.getAllGamesTiedByUser(username)
            }
            activity?.runOnUiThread {
                //recyclerView
                adapter = MyMeetingsAdapter(meetings)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : MyMeetingsAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        //TODO: TUTAJ ZMIANA FRAGMENTU
                        println(position)
                        val action = UserProfileFragmentDirections.actionUserProfileFragmentToMeetingInfoFragment(meetings[position])
                        Navigation.findNavController(view).navigate(action)
                    }
                })
                stats_placeholderText.text = "Wins: ${gamesWon.size} Losses: ${gamesLost.size} Ties: ${gamesTied.size}"
            }
        }.start()
        //Lichess
        var lichessNick = loggedUser.getString("lichessNick")
        if (lichessNick == "null") {
            lichessNick = "No Lichess Account Attached\n(click to attach account)"
        }else{
            Thread(){
                run {
                    userJson = restHelper.getLichessUserInfo(lichessNick)
                }
                activity?.runOnUiThread {
                    //lichess
                    val lichessProfileURL = userJson.getString("url")
                    val perfs = userJson.getJSONObject("perfs")
                    val classicalRank = perfs.getJSONObject("classical").getString("rating")
                    val rapidRank = perfs.getJSONObject("rapid").getString("rating")
                    val blitzRank = perfs.getJSONObject("blitz").getString("rating")
                    lichess_placeholderText.text = "Lichess Stats:\nUsername: ${lichessNick}\nClassical rating: ${classicalRank}\nRapid rating: ${rapidRank}\nBlitz rating: ${blitzRank}"
                    lichessInfoView.setOnClickListener(){
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(lichessProfileURL)))
                    }
                }
            }.start()
        }
        lichess_placeholderText.text = lichessNick
        //TODO dodanie konta Lichess
        println(lichessNick)
        if (lichessNick == "No Lichess Account Attached\n" +
            "(click to attach account)") {
            lichessInfoView.setOnClickListener(){
                Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_addLichessAccountFragment)
            }
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