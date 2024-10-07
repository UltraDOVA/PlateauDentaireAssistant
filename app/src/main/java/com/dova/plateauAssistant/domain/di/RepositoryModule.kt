package com.dova.plateauAssistant.domain.di

import android.content.Context
import com.dova.plateauAssistant.data.PlateauxDatabase
import com.dova.plateauAssistant.domain.PlateauRepository
import com.dova.plateauAssistant.domain.PlateauRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePlateauRepository(
        @ApplicationContext context: Context
    ): PlateauRepository {
        return PlateauRepositoryImpl(PlateauxDatabase.getDatabase(context).plateauDao())
    }
}