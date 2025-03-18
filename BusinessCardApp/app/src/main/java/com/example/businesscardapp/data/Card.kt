package com.example.businesscardapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_cards")
data class Card (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val imagePath: String
)