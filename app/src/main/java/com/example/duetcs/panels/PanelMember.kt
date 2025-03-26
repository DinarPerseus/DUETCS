package com.example.duetcs.panels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "panel_members")
data class PanelMember(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val role: String,
    val currentDesignation: String,
    val series: String

)