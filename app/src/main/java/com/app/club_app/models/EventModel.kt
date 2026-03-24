package com.app.club_app.models

data class EventModel(
    val eventId: Int,
    val eventTitle: String,
    val eventDate: String,
    val eventLocation: String,
    val eventImageResId: Int,
    val eventStatus: EventStatus,
    val eventType: String,
    val isHot: Boolean = false
)

enum class EventStatus(val displayLabel: String) {
    UPCOMING("Sắp diễn ra"),
    ATTENDING("Đang tham gia"),
    FINISHED("Đã kết thúc")
}
