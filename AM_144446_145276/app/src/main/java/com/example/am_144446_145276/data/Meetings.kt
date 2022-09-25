package com.example.am_144446_145276.data

fun genMeetingList(): ArrayList<Meeting> {
    val tmpList : ArrayList<Meeting> = ArrayList<Meeting>()
    for (i in 1..3){
        tmpList.add(Meeting(
            0,
            "Marcin",
            "Ukasz",
            "52.408054770716774, 16.93414070764418",
            "30-09-2022",
            "Kurwa nie wiem"
        ))
        tmpList.add(Meeting(
            1,
            "Ukasz",
            "Marcin",
            "52.408054770716774, 17.093421231",
            "01-10-2022",
            "Kurwa nie wiem"
        ))
        tmpList.add(Meeting(
            2,
            "Michal",
            "Wojtek",
            "53.408054770716774, 16.93414070764418",
            "29-09-2022",
            "aaaaaaaaaaaaaaaaaaaaaa"
        ))
    }
    return tmpList
}
fun genMeetingList2(): ArrayList<Meeting> {
    val tmpList : ArrayList<Meeting> = ArrayList<Meeting>()
    for (i in 1..3){
        tmpList.add(Meeting(
            0,
            "Martyna",
            "Agata",
            "52.408054770716774, 16.93414070764418",
            "29-09-2022",
            "Kurwa nie wiem"
        ))
        tmpList.add(Meeting(
            1,
            "Mati",
            "Dawid",
            "52.408054770716774, 17.093421231",
            "29-09-2022",
            "Kurwa nie wiem"
        ))
        tmpList.add(Meeting(
            2,
            "Michal",
            "Alex",
            "53.408054770716774, 16.93414070764418",
            "29-09-2022",
            "aaaaaaaaaaaaaaaaaaaaaa"
        ))
    }

    return tmpList
}