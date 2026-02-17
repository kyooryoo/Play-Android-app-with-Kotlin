package com.example.fruitties.kmptutorial.android.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return appDatabase(context)
    }

    @Provides
    fun providesFruittieDao(appDatabase: AppDatabase) = appDatabase.fruittieDao()

    @Provides
    fun providesCartDao(appDatabase: AppDatabase) = appDatabase.cartDao()
}

fun appDatabase(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath("sharedfruits.db")
    return Room
        .databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}