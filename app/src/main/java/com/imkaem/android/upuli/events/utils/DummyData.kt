package com.imkaem.android.upuli.events.utils

import com.imkaem.android.upuli.events.domain.models.EventModel
import java.time.LocalDateTime
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
        date = LocalDateTime.now(),
    ),
    EventModel(
        id = 2,
        title = "Koncert u Domu kulture",
        location = "Dom kulture",
        date = LocalDateTime.now(),
    ),
    EventModel(
        id = 3,
        title = "Izložba u Umjetničkoj galeriji",
        location = "Umjetnička galerija",
        date = LocalDateTime.now().plusDays(1),
    ),
    EventModel(
        id = 4,
        title = "Predavanje o održivom razvoju",
        location = "Sveučilište",
        date = LocalDateTime.now().plusDays(1)
    ),
    EventModel(
        id = 5,
        title = "Sportski turnir u dvorani",
        location = "Gradska dvorana",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 6,
        title = "Konferencija o tehnologiji",
        location = "Tehnički fakultet",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 7,
        title = "Radionica kreativnog pisanja",
        location = "Književni klub",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 8,
        title = "Filmska projekcija u kinu",
        location = "Gradsko kino",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 9,
        title = "Glazbeni festival u parku",
        location = "Gradski park",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 10,
        title = "Predstavljanje nove knjige u knjižari",
        location = "Gradska knjižara",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 11,
        title = "Humanitarni događaj u centru grada",
        location = "Trg bana Jelačića",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 12,
        title = "Tečaj plesa u plesnoj školi",
        location = "Plesna škola",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 13,
        title = "Predavanje o povijesti grada",
        location = "Gradski muzej",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 14,
        title = "Koncert klasične glazbe u koncertnoj dvorani",
        location = "Koncertna dvorana",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 15,
        title = "Izložba fotografija u galeriji",
        location = "Gradska galerija",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 16,
        title = "Predavanje o ekologiji u ekološkom centru",
        location = "Ekološki centar",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 17,
        title = "Koncert rock grupe u klubu",
        location = "Glazbeni klub",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 18,
        title = "Predavanje o zdravlju i prehrani",
        location = "Zdravstveni centar",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 19,
        title = "Izložba suvremene umjetnosti u galeriji",
        location = "Suvremena galerija",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 20,
        title = "Predavanje o digitalnoj transformaciji",
        location = "Tehnički inkubator",
        date = LocalDateTime.now().plusDays(2)
    ),
    EventModel(
        id = 21,
        title = "Koncert jazz sastava u klubu",
        location = "Jazz klub",
        date = LocalDateTime.now().plusDays(3)
    ),
    EventModel(
        id = 22,
        title = "Predavanje o financijskoj pismenosti",
        location = "Financijski centar",
        date = LocalDateTime.now().plusDays(3)
    ),
    EventModel(
        id = 23,
        title = "Izložba skulptura u parku",
        location = "Gradski park",
        date = LocalDateTime.now().plusDays(3)
    ),
    EventModel(
        id = 24,
        title = "Predavanje o umjetnoj inteligenciji",
        location = "Tehnički fakultet",
        date = LocalDateTime.now().plusDays(4)
    ),
    EventModel(
        id = 25,
        title = "Koncert etno glazbe u kulturnom centru",
        location = "Kulturni centar",
        date = LocalDateTime.now().plusDays(4)
    ),
    EventModel(
        id = 26,
        title = "Predavanje o održivoj modi",
        location = "Modni studio",
        date = LocalDateTime.now().plusDays(4)
    ),
    EventModel(
        id = 27,
        title = "Izložba keramike u galeriji",
        location = "Keramička galerija",
        date = LocalDateTime.now().plusDays(4)
    ),
)