package com.example.businesscardapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.businesscardapp.data.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Delete
    suspend fun delete(card: Card)

    @Query("SELECT * FROM business_cards ORDER BY id DESC")
    fun getAllCards(): LiveData<List<Card>>
}