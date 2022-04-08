package com.ab.relax.data.dao

import androidx.room.*
import com.ab.relax.data.entity.Photo


@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo): Long

    @Delete
    suspend fun delete(photo: Photo)

    @Query("SELECT * FROM photo WHERE user_id = :userId")
    suspend fun getPhotos(userId: Long): List<Photo>

}
