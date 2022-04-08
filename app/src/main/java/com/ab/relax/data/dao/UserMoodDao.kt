package com.ab.relax.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab.relax.data.entity.UserMood


@Dao
interface UserMoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userMood: UserMood)

    @Query("SELECT * FROM user_mood WHERE user_id = :userId")
    suspend fun getUserMoods(userId: Long): List<UserMood>

}
