package com.example.am_144446_145276.data

import android.content.res.Resources

fun genMeetingList(): ArrayList<Meeting> {
    val tmpList : ArrayList<Meeting> = ArrayList<Meeting>()
    tmpList.add(Meeting(
        1,
        "Marcin",
        "W parku na laweczce"
    ))
    tmpList.add(Meeting(
        2,
        "Łukasz",
        "Klatka 15 mieszkanie 5"
    ))
    tmpList.add(Meeting(
            3,
            "Wojtek",
            "W Bogdanowie, bo gdzie"
    ))
    tmpList.add(Meeting(
        1,
        "Marcin",
        "W parku na laweczce"
    ))
    tmpList.add(Meeting(
        2,
        "Łukasz",
        "Klatka 15 mieszkanie 5"
    ))
    tmpList.add(Meeting(
        3,
        "Wojtek",
        "W Bogdanowie, bo gdzie"
    ))
    tmpList.add(Meeting(
        1,
        "Marcin",
        "W parku na laweczce"
    ))
    tmpList.add(Meeting(
        2,
        "Łukasz",
        "Klatka 15 mieszkanie 5"
    ))
    tmpList.add(Meeting(
        3,
        "Wojtek",
        "W Bogdanowie, bo gdzie"
    ))
    tmpList.add(Meeting(
        1,
        "Marcin",
        "W parku na laweczce"
    ))
    tmpList.add(Meeting(
        2,
        "Łukasz",
        "Klatka 15 mieszkanie 5"
    ))
    tmpList.add(Meeting(
        3,
        "Wojtek",
        "W Bogdanowie, bo gdzie"
    ))
    return tmpList
}
fun genMeetingList2(): ArrayList<Meeting> {
    val tmpList : ArrayList<Meeting> = ArrayList<Meeting>()
    for (i in 1..3){
        tmpList.add(Meeting(
            3,
            "Wojtek",
            "W Bogdanowie, bo gdzie"
        ))
        tmpList.add(Meeting(
            3,
            "Hugo Boss",
            "W Hugolandii, bo gdzie"
        ))
        tmpList.add(Meeting(
            3,
            "typ z lego",
            "W Legolandzie, bo gdzie"
        ))
    }

    return tmpList
}