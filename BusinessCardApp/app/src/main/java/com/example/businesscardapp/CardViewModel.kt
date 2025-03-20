package com.example.businesscardapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.businesscardapp.data.Card
import com.example.businesscardapp.data.CardDatabase
import com.example.businesscardapp.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardRepository
    val allCards: LiveData<List<Card>>

    init {
        val cardDao = CardDatabase.getDatabase(application).cardDao()
        repository = CardRepository(cardDao)
        allCards = repository.allCards
    }

    fun insert(card: Card) = viewModelScope.launch {
        repository.insert(card)
    }
}