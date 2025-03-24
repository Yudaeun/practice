package com.example.businesscardapp.repository

import androidx.lifecycle.LiveData
import com.example.businesscardapp.dao.CardDao
import com.example.businesscardapp.data.Card

class CardRepository(private val cardDao: CardDao) {
    val allCards: LiveData<List<Card>> = cardDao.getAllCards()

    suspend fun insert(card: Card) {
        cardDao.insert(card)
    }

    suspend fun delete(card: Card){
        cardDao.delete(card)
    }
}