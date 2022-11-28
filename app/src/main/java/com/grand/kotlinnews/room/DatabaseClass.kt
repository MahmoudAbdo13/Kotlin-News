package com.grand.kotlinnews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redditnews.room.Entity

@Database(entities = [Entity::class], version = 2)
abstract class DatabaseClass : RoomDatabase() {
    abstract fun mDao(): Dao
    companion object
       {

           @Synchronized
           fun getInstance(mCtx: Context): DatabaseClass {
               return Room.databaseBuilder(
                   mCtx.applicationContext,
                   DatabaseClass::class.java, "news"
               )
                   .fallbackToDestructiveMigration()
                   .allowMainThreadQueries()
                   .build()
           }
       }
}