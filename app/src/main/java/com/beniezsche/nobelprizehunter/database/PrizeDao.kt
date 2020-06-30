package com.beniezsche.nobelprizehunter.database

import androidx.room.*
import com.beniezsche.nobelprizehunter.models.Prize


@Dao
interface PrizeDao {
    @Query("SELECT * FROM Prize")
    fun getAll(): List<Prize>

    @Query("SELECT * FROM Prize WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Prize>

    @Query("SELECT * FROM Prize WHERE category=:category")
    fun selectPrizeByCategory(category: String): List<Prize>

    @Query("SELECT * FROM Prize WHERE year=:year")
    fun selectPrizeByYear(year: String): List<Prize>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg prizes: Prize)

    @Delete
    fun delete(prize: Prize)

    @Query("DELETE FROM Prize")
    fun deleteAll()
}