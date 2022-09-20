package com.example.am_144446_145276.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
//    private val initialMeetingList = meetingList(resources)
//    private val meetingLiveData = MutableLiveData(initialMeetingList)
//
//    /* Adds flower to liveData and posts value. */
//    fun addMeeting(meeting: Meeting) {
//        val currentList = meetingLiveData.value
//        if (currentList == null) {
//            meetingLiveData.postValue(listOf(meeting))
//        } else {
//            val updatedList = currentList.toMutableList()
//            updatedList.add(0, meeting)
//            meetingLiveData.postValue(updatedList)
//        }
//    }
//
//    /* Removes flower from liveData and posts value. */
//    fun removeMeeting(meeting: Meeting) {
//        val currentList = meetingLiveData.value
//        if (currentList != null) {
//            val updatedList = currentList.toMutableList()
//            updatedList.remove(meeting)
//            meetingLiveData.postValue(updatedList)
//        }
//    }
//
//    /* Returns flower given an ID. */
//    fun getMeetingForId(id: Long): Meeting? {
//        meetingLiveData.value?.let { flowers ->
//            return flowers.firstOrNull{ it.id == id}
//        }
//        return null
//    }
//
//    fun getMeetingList(): LiveData<List<Meeting>> {
//        return meetingLiveData
//    }
//
//    companion object {
//        private var INSTANCE: DataSource? = null
//
//        fun getDataSource(resources: Resources): DataSource {
//            return synchronized(DataSource::class) {
//                val newInstance = INSTANCE ?: DataSource(resources)
//                INSTANCE = newInstance
//                newInstance
//            }
//        }
//    }
}