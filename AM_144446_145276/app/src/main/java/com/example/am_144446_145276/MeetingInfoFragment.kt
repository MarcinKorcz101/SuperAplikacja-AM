package com.example.am_144446_145276

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.graphics.Color
import androidx.navigation.fragment.navArgs
import com.example.am_144446_145276.data.Meeting
import com.example.am_144446_145276.helpers.RestHelper
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MeetingInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeetingInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val args by navArgs<MeetingInfoFragmentArgs>()

    private val restHelper = RestHelper()

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
        val sharedHelper = SharedPreferencesHelper(requireContext())
        val loggedUser = sharedHelper.getLoggedUser()

        val view =  inflater.inflate(R.layout.fragment_meeting_info, container, false)
        val meetingDateText = view.findViewById<TextView>(R.id.meetingDateText)
        val meetingHostnameText = view.findViewById<TextView>(R.id.HostName)
        val meetingOpponentText = view.findViewById<TextView>(R.id.opponentName)
        val detailsText = view.findViewById<TextView>(R.id.details_text)
        val addressText = view.findViewById<TextView>(R.id.address_text)

        val hostResult = view.findViewById<Button>(R.id.result_host)
        val tieResult = view.findViewById<Button>(R.id.result_tie)
        val opponentResult = view.findViewById<Button>(R.id.result_opponent)

        fun initButtons(){
            hostResult.setOnClickListener(){
                restHelper.updateMeetingRes(args.currentMeeting.id.toString(), hostResult.text.toString())
                meetingHostnameText.setBackgroundColor(Color.parseColor("#00FF00"))
                meetingOpponentText.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            tieResult.setOnClickListener(){
                restHelper.updateMeetingRes(args.currentMeeting.id.toString(), tieResult.text.toString())
                meetingHostnameText.setBackgroundColor(Color.parseColor("#FFFF00"))
                meetingOpponentText.setBackgroundColor(Color.parseColor("#FFFF00"))
            }
            opponentResult.setOnClickListener(){
                restHelper.updateMeetingRes(args.currentMeeting.id.toString(), opponentResult.text.toString())
                meetingHostnameText.setBackgroundColor(Color.parseColor("#FFFFFF"))
                meetingOpponentText.setBackgroundColor(Color.parseColor("#00FF00"))
            }
        }

        meetingDateText.text = args.currentMeeting.dateTime
        meetingHostnameText.text = args.currentMeeting.hostName
        meetingOpponentText.text = if (args.currentMeeting.opponent == "null") "" else args.currentMeeting.opponent
        detailsText.text = args.currentMeeting.details
        addressText.text = args.currentMeeting.address

        hostResult.text = meetingHostnameText.text
        tieResult.text = "tie"
        opponentResult.text = meetingOpponentText.text

        hostResult.visibility = View.INVISIBLE
        tieResult.visibility = View.INVISIBLE
        opponentResult.visibility = View.INVISIBLE

        if (args.currentMeeting.result != "null"){
            when (args.currentMeeting.result){
                meetingHostnameText.text -> {
                    meetingHostnameText.setBackgroundColor(Color.parseColor("#00FF00"))
                    meetingOpponentText.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
                tieResult.text -> {
                    meetingHostnameText.setBackgroundColor(Color.parseColor("#FFFF00"))
                    meetingOpponentText.setBackgroundColor(Color.parseColor("#FFFF00"))
                }
                meetingOpponentText.text -> {
                    meetingHostnameText.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    meetingOpponentText.setBackgroundColor(Color.parseColor("#00FF00"))
                }
            }
        }

        if (meetingOpponentText.text == "" && loggedUser.getString("username") != meetingHostnameText.text.toString()){
            meetingOpponentText.text = "Click to join"
            meetingOpponentText.setOnClickListener(){
                restHelper.updateMeetingOppo(args.currentMeeting.id.toString(), loggedUser.getString("username"))
                meetingOpponentText.text = loggedUser.getString("username")
                opponentResult.text = meetingOpponentText.text
                if(loggedUser.getString("username") == meetingHostnameText.text.toString()) {
                    hostResult.visibility = View.VISIBLE
                    tieResult.visibility = View.VISIBLE
                    opponentResult.visibility = View.VISIBLE
                    initButtons()
                }
            }
        }

        if (meetingOpponentText.text != "Click to join" &&
            args.currentMeeting.opponent != "null" &&
            loggedUser.getString("username") == meetingHostnameText.text.toString()){
            hostResult.visibility = View.VISIBLE
            tieResult.visibility = View.VISIBLE
            opponentResult.visibility = View.VISIBLE
            initButtons()
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
         * @return A new instance of fragment MeetingInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MeetingInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}