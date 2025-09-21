package org.sopt.android_alertcare.presentation.util

fun formatMinutes(minutes: Int): String {
    return when {
        minutes == 0 -> "0분"
        minutes < 60 -> "${minutes}분"
        else -> {
            val hours = minutes / 60
            val mins = minutes % 60
            if (mins == 0) {
                "${hours}시간"
            } else {
                "${hours}h ${mins}m"
            }
        }
    }
}
