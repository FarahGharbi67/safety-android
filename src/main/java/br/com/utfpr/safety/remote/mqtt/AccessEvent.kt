package br.com.utfpr.safety.remote.mqtt

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class AccessEvent(
    val type: EventType,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    val formattedTime: String
        get() = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .format(Date(timestamp))
}

enum class EventType {
    STATUS,
}
