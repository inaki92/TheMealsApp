package com.example.themealsapp.di

import com.example.themealsapp.rest.MealsRepository
import com.example.themealsapp.rest.MealsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMealsRepository(
        mealsRepositoryImpl: MealsRepositoryImpl
    ): MealsRepository

}