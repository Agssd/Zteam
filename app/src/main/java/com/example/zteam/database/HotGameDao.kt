package com.example.zteam.database

import HotNewGameEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HotNewGameDao {
    @Query("SELECT * FROM hot_new_games")
    fun getAll(): Flow<List<HotNewGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<HotNewGameEntity>)
}