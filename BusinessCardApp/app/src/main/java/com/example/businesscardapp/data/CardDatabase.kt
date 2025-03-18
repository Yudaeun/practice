package com.example.businesscardapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.businesscardapp.dao.CardDao

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase(){
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getDatabase(context: Context): CardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "card_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}