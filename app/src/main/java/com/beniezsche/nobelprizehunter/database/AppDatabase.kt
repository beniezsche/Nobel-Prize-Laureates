package com.beniezsche.nobelprizehunter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beniezsche.nobelprizehunter.models.Prize

@Database(entities = arrayOf(Prize::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prizeDao(): PrizeDao
}
