package com.ab.relax.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ab.relax.data.dao.PhotoDao
import com.ab.relax.data.dao.UserDao
import com.ab.relax.data.dao.UserMoodDao
import com.ab.relax.data.entity.Photo
import com.ab.relax.data.entity.User
import com.ab.relax.data.entity.UserMood

@Database(entities = [User::class, UserMood::class, Photo::class], version = 1, exportSchema = false)
abstract class RelaxRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userMoodDao(): UserMoodDao
    abstract fun photoDao(): PhotoDao

    companion object {

        @Volatile
        private var INSTANCE: RelaxRoomDatabase? = null

        fun getDatabase(context: Context): RelaxRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        RelaxRoomDatabase::class.java,
                        "relax_database"
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
