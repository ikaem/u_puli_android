package com.imkaem.android.upuli.events.utils.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDateTime.croDateFormat(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.", Locale.getDefault())
    return this.format(dateFormatter)
}

fun LocalDateTime.monthYearFormat(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale("hr", "HR"))
    val formattedDate = this.format(dateFormatter).lowercase()

    return formattedDate.replace("siječnja", "siječanj").replace("veljače", "veljača")
        .replace("ožujka", "ožujak").replace("travnja", "travanj")
        .replace("svibnja", "svibanj").replace("lipnja", "lipanj")
        .replace("srpnja", "srpanj").replace("kolovoza", "kolovoz")
        .replace("rujna", "rujan").replace("listopada", "listopad")
        .replace("studenoga", "studeni").replace("prosinca", "prosinac")
}

fun LocalDateTime.toYearMonth(): LocalDateTime {
    val yearMonthDate = LocalDateTime.of(
        this.year,
        this.month,
        1,
        0, 0, 0, 0,
    )

    return yearMonthDate
}