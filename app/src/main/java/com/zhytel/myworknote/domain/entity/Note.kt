package com.zhytel.myworknote.domain.entity

data class Note(
    var id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    val time: String,
    val day: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}