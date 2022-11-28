package com.grand.kotlinnews.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.redditnews.room.Entity

@Dao
interface Dao {

    @Query("select * from news")
    fun getlist():List<Entity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertNews(entity: Entity)


}