package com.ab.relax

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.ab.relax.data.RelaxRoomDatabase
import com.ab.relax.data.StoreUserId
import com.ab.relax.data.entity.User
import com.ab.relax.data.repository.PhotoRepository
import com.ab.relax.data.repository.UserMoodRepository
import com.ab.relax.data.repository.UserRepository
import com.ab.relax.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {

    var user: User? = null
    val database by lazy { RelaxRoomDatabase.getDatabase(this) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val userMoodRepository by lazy { UserMoodRepository(database.userMoodDao()) }
    val photoRepository by lazy { PhotoRepository(database.photoDao()) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        instance = this
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }

    companion object {
        lateinit var instance: App

        suspend fun saveUserId(context: Context) {
            instance.user?.let {
                StoreUserId(context).saveUserId(it.id)
            }
        }
    }
}