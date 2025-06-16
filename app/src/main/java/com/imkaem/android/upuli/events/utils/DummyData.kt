package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.domain.models.EventModel
import java.util.Date

object DummyData {
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

private val events: List<EventModel> = listOf(
    EventModel(
        id = 1,
        title = "Nekakav događaj koji se događa u Gradskoj knjižnici",
        location = "Gradska knjižnica",
        date = Date(),
    ),
    EventModel(
        id = 2,
        title = "Koncert u Domu kulture",
        location = "Dom kulture",
        date = Date(),
    ),
    EventModel(
        id = 3,
        title = "Izložba u Umjetničkoj galeriji",
        location = "Umjetnička galerija",
        date = Date(),
    ),
    EventModel(
        id = 4,
        title = "Predavanje o održivom razvoju",
        location = "Sveučilište",
        date = Date(),
    ),
    EventModel(
        id = 5,
        title = "Sportski turnir u dvorani",
        location = "Gradska dvorana",
        date = Date(),
    ),
    EventModel(
        id = 6,
        title = "Konferencija o tehnologiji",
        location = "Tehnički fakultet",
        date = Date(),
    ),
    EventModel(
        id = 7,
        title = "Radionica kreativnog pisanja",
        location = "Književni klub",
        date = Date(),
    ),
    EventModel(
        id = 8,
        title = "Filmska projekcija u kinu",
        location = "Gradsko kino",
        date = Date(),
    ),
    EventModel(
        id = 9,
        title = "Glazbeni festival u parku",
        location = "Gradski park",
        date = Date(),
    ),
    EventModel(
        id = 10,
        title = "Predstavljanje nove knjige u knjižari",
        location = "Gradska knjižara",
        date = Date(),
    ),
    EventModel(
        id = 11,
        title = "Humanitarni događaj u centru grada",
        location = "Trg bana Jelačića",
        date = Date(),
    ),
    EventModel(
        id = 12,
        title = "Tečaj plesa u plesnoj školi",
        location = "Plesna škola",
        date = Date(),
    ),
    EventModel(
        id = 13,
        title = "Predavanje o povijesti grada",
        location = "Gradski muzej",
        date = Date(),
    ),
    EventModel(
        id = 14,
        title = "Koncert klasične glazbe u koncertnoj dvorani",
        location = "Koncertna dvorana",
        date = Date(),
    ),
    EventModel(
        id = 15,
        title = "Izložba fotografija u galeriji",
        location = "Gradska galerija",
        date = Date(),
    ),
    EventModel(
        id = 16,
        title = "Predavanje o ekologiji u ekološkom centru",
        location = "Ekološki centar",
        date = Date(),
    ),
    EventModel(
        id = 17,
        title = "Koncert rock grupe u klubu",
        location = "Glazbeni klub",
        date = Date(),
    ),
    EventModel(
        id = 18,
        title = "Predavanje o zdravlju i prehrani",
        location = "Zdravstveni centar",
        date = Date(),
    ),
    EventModel(
        id = 19,
        title = "Izložba suvremene umjetnosti u galeriji",
        location = "Suvremena galerija",
        date = Date(),
    ),
    EventModel(
        id = 20,
        title = "Predavanje o digitalnoj transformaciji",
        location = "Tehnički inkubator",
        date = Date(),
    ),
    EventModel(
        id = 21,
        title = "Koncert jazz sastava u klubu",
        location = "Jazz klub",
        date = Date(),
    ),
    EventModel(
        id = 22,
        title = "Predavanje o financijskoj pismenosti",
        location = "Financijski centar",
        date = Date(),
    ),
    EventModel(
        id = 23,
        title = "Izložba skulptura u parku",
        location = "Gradski park",
        date = Date(),
    ),
    EventModel(
        id = 24,
        title = "Predavanje o umjetnoj inteligenciji",
        location = "Tehnički fakultet",
        date = Date(),
    ),
    EventModel(
        id = 25,
        title = "Koncert etno glazbe u kulturnom centru",
        location = "Kulturni centar",
        date = Date(),
    ),
    EventModel(
        id = 26,
        title = "Predavanje o održivoj modi",
        location = "Modni studio",
        date = Date(),
    ),
    EventModel(
        id = 27,
        title = "Izložba keramike u galeriji",
        location = "Keramička galerija",
        date = Date(),
    ),
)