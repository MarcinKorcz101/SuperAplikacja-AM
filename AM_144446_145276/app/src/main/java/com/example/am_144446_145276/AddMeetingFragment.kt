package com.example.am_144446_145276

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.am_144446_145276.helpers.RestHelper
import com.example.am_144446_145276.helpers.SharedPreferencesHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddMeetingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMeetingFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinutes = 0
    var selectedDateTime = LocalDateTime.now()

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
        val view = inflater.inflate(R.layout.fragment_add_meeting, container, false)
        val createMeetingBtn = view.findViewById<Button>(R.id.createMeetingBtn)
        val detailsText = view.findViewById<TextView>(R.id.detailsOfMeeting)
        val addressText = view.findViewById<TextView>(R.id.addressOfMeeting)
        val restHelper = RestHelper()
        val sharedHelper = SharedPreferencesHelper(requireContext())
        val loggedUser = sharedHelper.getLoggedUser()
        view.findViewById<TextView>(R.id.dateOfMeeting).text = "Date of meeting: ${selectedDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}"
        val username = loggedUser.getString("username")

        val currentDate = LocalDateTime.now()

        createMeetingBtn.setOnClickListener{
            if (detailsText.text.toString() == ""){
                Toast.makeText(
                    requireContext(),
                    "Please attach details",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (detailsText.text.toString().length > 255) {
                Toast.makeText(
                    requireContext(),
                    "Details cannot exceed 255 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (currentDate.isAfter(selectedDateTime)) {
                Toast.makeText(
                    requireContext(),
                    "Please choose date after current date",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (addressText.text.toString() == "") {
                Toast.makeText(
                    requireContext(),
                    "Please attach address",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (addressText.text.toString().length > 100) {
                Toast.makeText(
                    requireContext(),
                    "Address cannot exceed 100 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                restHelper.addGame(
                    username,
                    selectedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    addressText.text.toString(),
                    detailsText.text.toString()
                )
                Navigation.findNavController(view).navigate(R.id.action_addMeetingFragment_to_mainFragment)
            }
        }
        pickDate(view)

        return view
    }

    private fun getDateTimeCalandar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }
    private fun pickDate(view: View){
        val btn = view.findViewById<Button>(R.id.changeDateButton)
        getDateTimeCalandar()
        btn.setOnClickListener{
            DatePickerDialog(requireContext(), this, year, month, day).show()
//            view.findViewById<TextView>(R.id.dateOfMeeting).text =
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddMeetingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMeetingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDateSet(v: DatePicker?, year: Int, month: Int, day: Int) {
        savedDay = day
        savedMonth = month+1
        savedYear = year

        println("$savedDay, $savedMonth $savedYear")
        getDateTimeCalandar()

        TimePickerDialog(requireContext(), this, hour, minute, true).show()
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinutes = minute
        selectedDateTime = LocalDateTime.parse("${savedDay.toString().padStart(2,'0')}-${savedMonth.toString().padStart(2,'0')}-$savedYear ${savedHour.toString().padStart(2,'0')}:${savedMinutes.toString().padStart(2,'0')}",
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        val dateTimeStr = selectedDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        println(dateTimeStr)
        requireView().findViewById<TextView>(R.id.dateOfMeeting).text =
            "Date of meeting: $dateTimeStr"
    }
}