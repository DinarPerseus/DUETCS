package com.example.duetcs.panels

data class PanelMember(
    val designation: String,
    val name: String,
    val studentID: String,
    val series: String,
    val phone: String,
    val email: String,
    val currentDesignation: String = "", // Optional
    val photoUrl: String = "" // Optional

)

