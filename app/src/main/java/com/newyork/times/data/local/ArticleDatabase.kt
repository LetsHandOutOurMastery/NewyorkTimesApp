package com.newyork.times.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.newyork.times.model.Article
import com.newyork.times.model.Media
import com.newyork.times.model.MediaMetadata

@Database(
    version = 1,
    entities = [Article::class, Media::class, MediaMetadata::class],
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}