package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.data.remote.EventRemoteEntity
import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object DummyData {
    val dummyRemoteEvents: List<EventRemoteEntity> = remoteEntityEvents
    val dummyEvents: List<EventModel> = events
//    val dummyEvents = List<EventModel>(100) { index ->
////        val milliseconds = index * 1000
////        val seconds = milliseconds * 60
////        val minutes = seconds * 60
//        val oneHourInMilliseconds = 1000 * 60 * 60
//        val date = Date(Date().time + oneHourInMilliseconds )
//
//        EventModel(
//            id = index + 1,
//            title = "Event ${index + 1}",
//            location = "Location ${index + 1}",
//            date = date,
//        )
//    }
}
val nowDateTime: LocalDateTime = LocalDateTime.now()!!

private val remoteEntityEvents: List<EventRemoteEntity> = listOf(
    EventRemoteEntity(
        id = 1,
        title = "Nekakav događaj koji se događa u Gradskoj knjižnici",
        location = "Gradska knjižnica",
        dateInMilliseconds = nowDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 2,
        title = "Koncert u Domu kulture",
        location = "Dom kulture",
        dateInMilliseconds = nowDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 3,
        title = "Izložba u Umjetničkoj galeriji",
        location = "Umjetnička galerija",
        dateInMilliseconds = nowDateTime.plusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 4,
        title = "Predavanje o održivom razvoju",
        location = "Sveučilište",
        dateInMilliseconds = nowDateTime.plusDays(1).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 5,
        title = "Sportski turnir u dvorani",
        location = "Gradska dvorana",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 6,
        title = "Konferencija o tehnologiji",
        location = "Tehnički fakultet",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 7,
        title = "Radionica kreativnog pisanja",
        location = "Književni klub",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 8,
        title = "Filmska projekcija u kinu",
        location = "Gradsko kino",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 9,
        title = "Glazbeni festival u parku",
        location = "Gradski park",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 10,
        title = "Predstavljanje nove knjige u knjižari",
        location = "Gradska knjižara",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 11,
        title = "Humanitarni događaj u centru grada",
        location = "Trg bana Jelačića",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 12,
        title = "Tečaj plesa u plesnoj školi",
        location = "Plesna škola",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 13,
        title = "Predavanje o povijesti grada",
        location = "Gradski muzej",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 14,
        title = "Koncert klasične glazbe u koncertnoj dvorani",
        location = "Koncertna dvorana",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 15,
        title = "Izložba fotografija u galeriji",
        location = "Gradska galerija",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 16,
        title = "Predavanje o ekologiji u ekološkom centru",
        location = "Ekološki centar",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 17,
        title = "Koncert rock grupe u klubu",
        location = "Glazbeni klub",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 18,
        title = "Predavanje o zdravlju i prehrani",
        location = "Zdravstveni centar",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 19,
        title = "Izložba suvremene umjetnosti u galeriji",
        location = "Suvremena galerija",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 20,
        title = "Predavanje o digitalnoj transformaciji",
        location = "Tehnički inkubator",
        dateInMilliseconds = nowDateTime.plusDays(2).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 21,
        title = "Koncert jazz sastava u klubu",
        location = "Jazz klub",
        dateInMilliseconds = nowDateTime.plusDays(3).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 22,
        title = "Predavanje o financijskoj pismenosti",
        location = "Financijski centar",
        dateInMilliseconds = nowDateTime.plusDays(3).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 23,
        title = "Izložba skulptura u parku",
        location = "Gradski park",
        dateInMilliseconds = nowDateTime.plusDays(3).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 24,
        title = "Predavanje o umjetnoj inteligenciji",
        location = "Tehnički fakultet",
        dateInMilliseconds = nowDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 25,
        title = "Koncert etno glazbe u kulturnom centru",
        location = "Kulturni centar",
        dateInMilliseconds = nowDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 26,
        title = "Predavanje o održivoj modi",
        location = "Modni studio",
        dateInMilliseconds = nowDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),
    EventRemoteEntity(
        id = 27,
        title = "Izložba keramike u galeriji",
        location = "Keramička galerija",
        dateInMilliseconds = nowDateTime.plusDays(4).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli(),
    ),


)

val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())


private val events: List<EventModel> = listOf(
    EventModel(
        id = 1,
        title = "Nekakav događaj koji se događa u Gradskoj knjižnici",
        location = "Gradska knjižnica",
        date = nowDateTime.format(dateFormatter),
        time = nowDateTime.format(timeFormatter),
    ),
    EventModel(
        id = 2,
        title = "Koncert u Domu kulture",
        location = "Dom kulture",
        date = nowDateTime.format(dateFormatter),
        time = nowDateTime.format(timeFormatter),

        ),
    EventModel(
        id = 3,
        title = "Izložba u Umjetničkoj galeriji",
        location = "Umjetnička galerija",
        date = nowDateTime.plusDays(1).format(dateFormatter),
        time = nowDateTime.plusDays(1).format(timeFormatter),
    ),
    EventModel(
        id = 4,
        title = "Predavanje o održivom razvoju",
        location = "Sveučilište",
        date = nowDateTime.plusDays(1).format(dateFormatter),
        time = nowDateTime.plusDays(1).format(timeFormatter),
    ),
    EventModel(
        id = 5,
        title = "Sportski turnir u dvorani",
        location = "Gradska dvorana",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),

        ),
    EventModel(
        id = 6,
        title = "Konferencija o tehnologiji",
        location = "Tehnički fakultet",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 7,
        title = "Radionica kreativnog pisanja",
        location = "Književni klub",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 8,
        title = "Filmska projekcija u kinu",
        location = "Gradsko kino",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 9,
        title = "Glazbeni festival u parku",
        location = "Gradski park",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 10,
        title = "Predstavljanje nove knjige u knjižari",
        location = "Gradska knjižara",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 11,
        title = "Humanitarni događaj u centru grada",
        location = "Trg bana Jelačića",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 12,
        title = "Tečaj plesa u plesnoj školi",
        location = "Plesna škola",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 13,
        title = "Predavanje o povijesti grada",
        location = "Gradski muzej",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 14,
        title = "Koncert klasične glazbe u koncertnoj dvorani",
        location = "Koncertna dvorana",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 15,
        title = "Izložba fotografija u galeriji",
        location = "Gradska galerija",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 16,
        title = "Predavanje o ekologiji u ekološkom centru",
        location = "Ekološki centar",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 17,
        title = "Koncert rock grupe u klubu",
        location = "Glazbeni klub",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 18,
        title = "Predavanje o zdravlju i prehrani",
        location = "Zdravstveni centar",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 19,
        title = "Izložba suvremene umjetnosti u galeriji",
        location = "Suvremena galerija",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 20,
        title = "Predavanje o digitalnoj transformaciji",
        location = "Tehnički inkubator",
        date = nowDateTime.plusDays(2).format(dateFormatter),
        time = nowDateTime.plusDays(2).format(timeFormatter),
    ),
    EventModel(
        id = 21,
        title = "Koncert jazz sastava u klubu",
        location = "Jazz klub",
        date = nowDateTime.plusDays(3).format(dateFormatter),
        time = nowDateTime.plusDays(3).format(timeFormatter),
    ),
    EventModel(
        id = 22,
        title = "Predavanje o financijskoj pismenosti",
        location = "Financijski centar",
        date = nowDateTime.plusDays(3).format(dateFormatter),
        time = nowDateTime.plusDays(3).format(timeFormatter),
    ),
    EventModel(
        id = 23,
        title = "Izložba skulptura u parku",
        location = "Gradski park",
        date = nowDateTime.plusDays(3).format(dateFormatter),
        time = nowDateTime.plusDays(3).format(timeFormatter),
    ),
    EventModel(
        id = 24,
        title = "Predavanje o umjetnoj inteligenciji",
        location = "Tehnički fakultet",
        date = nowDateTime.plusDays(4).format(dateFormatter),
        time = nowDateTime.plusDays(4).format(timeFormatter),
    ),
    EventModel(
        id = 25,
        title = "Koncert etno glazbe u kulturnom centru",
        location = "Kulturni centar",
        date = nowDateTime.plusDays(4).format(dateFormatter),
        time = nowDateTime.plusDays(4).format(timeFormatter),
    ),
    EventModel(
        id = 26,
        title = "Predavanje o održivoj modi",
        location = "Modni studio",
        date = nowDateTime.plusDays(4).format(dateFormatter),
        time = nowDateTime.plusDays(4).format(timeFormatter),
    ),
    EventModel(
        id = 27,
        title = "Izložba keramike u galeriji",
        location = "Keramička galerija",
        date = nowDateTime.plusDays(4).format(dateFormatter),
        time = nowDateTime.plusDays(4).format(timeFormatter),
    ),
)