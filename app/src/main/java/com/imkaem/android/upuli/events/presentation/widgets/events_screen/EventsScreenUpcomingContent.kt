package com.imkaem.android.upuli.events.presentation.widgets.events_screen


/* TODO this entire file is not needed anymore */
//@Composable
//fun EventsScreenUpcomingContent(
//    events: List<EventModel>,
//    onNavigateToEvent: (Int) -> Unit,
//    onToggleEventIsBookmarked: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(ColorWhite)
//            .padding(all = 10.dp)
//    ) {
////        Text(
////            "SVI NADOLAZEĆI DOGAĐAJI",
////            fontSize = 14.sp,
////            fontWeight = FontWeight.Bold,
////        )
//        EventsContentTitle(
//            title = "SVI NADOLAZEĆI DOGAĐAJI",
//            markerColor = ColorGreyGreen60,
//            textColor = ColorGrey60,
//            bottomSpacing = 5.dp,
//        )
//        if (events.isEmpty()) {
//            EventsScreenUpcomingEventsEmptyContent()
//            return
//        }
//        EventsScreenUpcomingEventsPopulatedContent(
//            events = events,
//            onNavigateToEvent = onNavigateToEvent,
//            onToggleEventIsBookmarked = onToggleEventIsBookmarked,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}
//
///* TODO this could be reused probably */
//@Composable
//private fun EventsScreenUpcomingEventsEmptyContent(
//    modifier: Modifier = Modifier,
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier.fillMaxWidth()
//    ) {
//        Text(
//            "Nema nadolazećih događaja",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}
//
//@Composable
//private fun EventsScreenUpcomingEventsPopulatedContent(
//    events: List<EventModel>,
//    onNavigateToEvent: (Int) -> Unit,
//    onToggleEventIsBookmarked: (Int) -> Unit,
//    modifier: Modifier,
//) {
//    EventBriefItems(
//        events = events,
//        onNavigateToEvent = onNavigateToEvent,
//        onToggleEventIsBookmarked = onToggleEventIsBookmarked,
//        modifier = modifier,
//
//        )
//}
//
