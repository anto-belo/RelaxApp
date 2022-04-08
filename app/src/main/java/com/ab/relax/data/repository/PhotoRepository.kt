package com.ab.relax.data.repository

import androidx.annotation.WorkerThread
import com.ab.relax.data.dao.PhotoDao
import com.ab.relax.data.entity.Photo

class PhotoRepository(private val photoDao: PhotoDao) {

    @WorkerThread
    suspend fun insert(photo: Photo) = photoDao.insert(photo)

    @WorkerThread
    suspend fun delete(photo: Photo) {
        photoDao.delete(photo)
    }

    suspend fun getPhotos(userId: Long) = photoDao.getPhotos(userId)

}
