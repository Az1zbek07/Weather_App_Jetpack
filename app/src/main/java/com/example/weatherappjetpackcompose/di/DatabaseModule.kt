package com.example.weatherappjetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.weatherappjetpackcompose.data.database.WeatherDao
import com.example.weatherappjetpackcompose.data.database.WeatherDatabase
import com.example.weatherappjetpackcompose.data.manager.DataStoreManager
import com.example.weatherappjetpackcompose.data.repository.LocalRepositoryImpl
import com.example.weatherappjetpackcompose.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "Weather.db"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideWeatherDao(
        weatherDatabase: WeatherDatabase
    ): WeatherDao {
        return weatherDatabase.dao
    }

    @[Provides Singleton]
    fun provideLocalRepository(
        dao: WeatherDao,
        dataStoreManager: DataStoreManager
    ): LocalRepository {
        return LocalRepositoryImpl(dao,dataStoreManager)
    }
    @[Provides Singleton]
    fun provideDatastoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}