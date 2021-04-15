package com.example.birdcounter.di

import android.content.Context
import android.content.SharedPreferences
import com.example.birdcounter.utils.COUNTER_PREFERENCES_FILE_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class SharedPrefModule {


    @Qualifier // we add qualifier in case we add other shared preferences
    @Retention(AnnotationRetention.BINARY)
    annotation class CounterSharedPreferences


    @Singleton
    @CounterSharedPreferences
    @Provides
    fun provideCounterPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            COUNTER_PREFERENCES_FILE_KEY,
            Context.MODE_PRIVATE
        )


}