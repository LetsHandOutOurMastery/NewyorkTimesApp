package com.inhouse.nytimesarticleapp.di

import android.content.Context
import androidx.room.Room
import com.newyork.times.data.local.ArticleDao
import com.newyork.times.data.local.ArticleDatabase
import com.newyork.times.data.local.RoomConverters
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object TestDataModule {
    @Singleton
    @Provides
    fun typeConverter() = RoomConverters(Moshi.Builder().build())

    @Provides
    fun provideInMemoryDb(
        @ApplicationContext context: Context,
        typeConverter: RoomConverters
    ): ArticleDao =
        Room.inMemoryDatabaseBuilder(context, ArticleDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(typeConverter)
            .build().articleDao()
}