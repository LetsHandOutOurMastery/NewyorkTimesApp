package com.newyork.times.ui.main.di

import com.newyork.times.data.repository.MostPopularRepository
import com.newyork.times.data.repository.NYMostPopularRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MostPopularListModule {
    @ViewModelScoped
    @Binds
    abstract fun bindArticleRepository(repository: NYMostPopularRepository): MostPopularRepository
}